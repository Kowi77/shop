package kov.develop.mvc.service;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.repository.GoodRepository;

import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kov.develop.mvc.utils.Datalogger.DATA_LOGGER;

@Service
public class GoodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodService.class);

    private GoodRepository repository;

    @Autowired
    public GoodService(GoodRepository repository) {
        this.repository = repository;
    }

    @Cacheable("kov.develop.mvc.model.Good")
    public List<Good> findAll() {
        LOGGER.info("Recieving all goods for ADMIN");
        return repository.findAll();
    }

    @Cacheable("kov.develop.mvc.model.Good")
    public List<Good> findAllNotZeroQuantity() {
        LOGGER.info("Recieving all goods for USER");
        return repository.findAllByQuantityGreaterThan(0);
    }

    @CacheEvict("kov.develop.mvc.model.Good")
    @Transactional
    public Good save(Good good) {
        Good good1 = repository.save(good);
     if (good1 != null) {
            DATA_LOGGER.info(good1.toString() + " successfully saved");
        } else {
         LOGGER.warn("Good saving was failing!");
     }
        return good1;
    }

    @CacheEvict("kov.develop.mvc.model.Good")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Good purchase(Good good) {
        Good goodDb = get(good.getId());
        Integer quantity = goodDb.getQuantity();
        Good good1 = null;
        if (goodDb.equals(good)) {
            LOGGER.error("Purchasing was failed, attemp of illegal good's attributes using");
            return good1;
        } else if (goodDb.getQuantity() >= good.getQuantity()) {
                good.setQuantity(quantity - good.getQuantity());
                good1 = save(good);
        }
        if (good1 != null) {
            LOGGER.info("Purchasing for good {} successfully finished", good1.getName());
        } else {
            LOGGER.warn("Purchasing was failed!");
        }
        return good1;
    }

    @Cacheable("kov.develop.mvc.model.Good")
    public Good get(Integer integer) {
        return repository.findOne(integer);
    }

    @CacheEvict("kov.develop.mvc.model.Good")
    @Transactional
    public void delete(Integer id) {
        DATA_LOGGER.info("Good with ID " + id + "successfully deleted");
        repository.delete(id);
    }

    @CacheEvict("kov.develop.mvc.model.Good")
    public void evictCache() {
        // only for evict cache
    }
}
