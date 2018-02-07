package kov.develop.mvc.repository;

import kov.develop.mvc.model.Purchasing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PurchasingRepository extends JpaRepository<Purchasing, Integer> {

    @Override
    List<Purchasing> findAll();

    @Override
    @Transactional
    Purchasing save(Purchasing purchasing);

    @Override
    Purchasing getOne(Integer integer);

    @Override
    Purchasing findOne(Integer integer);
}
