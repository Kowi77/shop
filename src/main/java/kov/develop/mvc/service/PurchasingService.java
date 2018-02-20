package kov.develop.mvc.service;

import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.model.PurchasingDto;
import kov.develop.mvc.repository.GoodRepository;
import kov.develop.mvc.repository.PurchasingRepository;
import kov.develop.mvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static kov.develop.mvc.utils.Datalogger.DATA_LOGGER;

@Service
public class PurchasingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchasingService.class);
    private static final LinkedList<Purchasing> delayedPurchasings = new LinkedList<>();

    @PersistenceContext
    private EntityManager em;

    public PurchasingRepository repository;
    public UserRepository userRepository;
    public GoodRepository goodRepository;

    @Autowired
    public PurchasingService(PurchasingRepository repository) {

        this.repository = repository;
        Thread purchasingDeamon = new Thread(new PurchasingDeamon());
        purchasingDeamon.setDaemon(true);
        purchasingDeamon.start();
    }

    @Transactional
    public Purchasing save(Purchasing purchasing) {
        Purchasing purchasing1 = repository.save(purchasing);
        if (purchasing1 != null) {
            DATA_LOGGER.info(purchasing1.toString() + " successfully saved");
        } else {
            LOGGER.warn("Purchaising saving failed, it was put in queue!");
            delayedPurchasings.addLast(purchasing);
        }
        return purchasing1;
    }

    public List<PurchasingDto> findAllDto() {
        LOGGER.info("Recieve all purchasing");
        List<PurchasingDto> list = em.createNamedQuery(PurchasingDto.GET_ALL, PurchasingDto.class).getResultList();
        delayedPurchasings.forEach(p -> list.add(new PurchasingDto(null, userRepository.getOne(p.getUser_id()).getUsername(),
                goodRepository.findOne(p.getGood_id()).getName(), p.getDate(), p.getPrice(), p.getQuantity())));
        return list.stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).collect(Collectors.toList());
    }

    private class PurchasingDeamon implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (!delayedPurchasings.isEmpty()) {
                    Purchasing purchasing = save(delayedPurchasings.peekFirst());
                    if (purchasing != null) {
                        delayedPurchasings.pollFirst();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
