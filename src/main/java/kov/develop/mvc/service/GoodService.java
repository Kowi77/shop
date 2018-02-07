package kov.develop.mvc.service;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.repository.GoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {


    private GoodRepository repository;

    @Autowired
    public GoodService(GoodRepository repository) {
        this.repository = repository;
    }

    public List<Good> findAll() {
        return repository.findAll();
    }

    public Good save(Good good) {
        return repository.save(good);
    }

    public Good get(Integer integer) {
        return repository.findOne(integer);
    }
}
