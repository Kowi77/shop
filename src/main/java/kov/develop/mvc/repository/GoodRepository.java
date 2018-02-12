package kov.develop.mvc.repository;

import kov.develop.mvc.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Integer> {

    @Override
    List<Good> findAll();

    List<Good> findAllByQuantityGreaterThan(Integer i);

    @Override
    Good save(Good good);

    @Override
    Good findOne(Integer integer);

    @Override
    void delete(Integer integer);
}