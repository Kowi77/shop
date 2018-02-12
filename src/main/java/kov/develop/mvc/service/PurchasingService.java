package kov.develop.mvc.service;

import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.model.PurchasingDto;
import kov.develop.mvc.repository.PurchasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PurchasingService {


    @PersistenceContext
    private EntityManager em;

    @Autowired
    public PurchasingRepository repository;

    public List<Purchasing> findAll() {
        return repository.findAll();
    }

    public Purchasing save(Purchasing purchasing) {
        return repository.save(purchasing);
    }

    public List<PurchasingDto> findAllDto() {
        return em.createNamedQuery(PurchasingDto.GET_ALL, PurchasingDto.class).getResultList();
    }

    public Purchasing get(Integer integer) {
        return repository.findOne(integer);
    }
}
