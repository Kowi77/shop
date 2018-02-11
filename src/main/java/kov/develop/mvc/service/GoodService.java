package kov.develop.mvc.service;

import kov.develop.mvc.model.Good;
import kov.develop.mvc.repository.GoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Good save(Good good) {
        return repository.save(good);
    }

    @Transactional
    public Good purchase(Good good) {
        Integer quantity = get(good.getId()).getQuantity();
        if (quantity >= good.getQuantity()) {
            try {
                good.setQuantity(quantity - good.getQuantity());
                return save(good);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public Good get(Integer integer) {
        return repository.findOne(integer);
    }

    public void delete(Integer id) { repository.delete(id);}
}
