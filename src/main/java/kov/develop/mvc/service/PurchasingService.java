package kov.develop.mvc.service;

import kov.develop.mvc.model.Purchasing;
import kov.develop.mvc.repository.PurchasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasingService {

    @Autowired
    public PurchasingRepository repository;

    public List<Purchasing> findAll() {
        return repository.findAll();
    }

    public Purchasing save(Purchasing purchasing) {
        return repository.save(purchasing);
    }

    public Purchasing get(Integer integer) {
        return repository.findOne(integer);
    }
}
