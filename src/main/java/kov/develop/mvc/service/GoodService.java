package kov.develop.mvc.service;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.repository.GoodRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Good> findAll() {
        LOGGER.info("Recieving all goods for ADMIN");
        return repository.findAll();
    }

    public List<Good> findAllNotZeroQuantity() {
        LOGGER.info("Recieving all goods for USER");
        return repository.findAllByQuantityGreaterThan(0);
    }

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

    @Transactional
    public Good purchase(Good good) {
        Integer quantity = get(good.getId()).getQuantity();
        Good good1 = null;
        if (quantity >= good.getQuantity()) {
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

    public Good get(Integer integer) {
        return repository.findOne(integer);
    }

    @Transactional
    public void delete(Integer id) {
        DATA_LOGGER.info("Good with ID " + id + "successfully deleted");
        repository.delete(id);
    }
}
