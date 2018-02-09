package kov.develop.mvc.repository;

import kov.develop.mvc.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Integer> {

    @Override
    List<Good> findAll();

    @Override
    @Transactional
    Good save(Good good);

    @Override
    Good getOne(Integer integer);

    @Override
    Good findOne(Integer integer);

    @Override
    void delete(Integer integer);
}