package kov.develop.mvc.service;

import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.model.PurchasingDto;
import kov.develop.mvc.repository.PurchasingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static kov.develop.mvc.utils.Datalogger.DATA_LOGGER;

@Service
public class PurchasingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchasingService.class);

    @PersistenceContext
    private EntityManager em;

    public PurchasingRepository repository;

    @Autowired
    public PurchasingService(PurchasingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Purchasing save(Purchasing purchasing) {
        Purchasing purchasing1 = repository.save(purchasing);
        if (purchasing1 != null) {
            DATA_LOGGER.info(purchasing1.toString() + " successfully saved");
        } else {
            LOGGER.warn("Purchaising was failed!");
        }
        return purchasing1;
    }

    public List<PurchasingDto> findAllDto() {
        LOGGER.info("Recieve all purchasing");
        return em.createNamedQuery(PurchasingDto.GET_ALL, PurchasingDto.class).getResultList();
    }
}
