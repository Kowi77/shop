package kov.develop.mvc.repository;

import kov.develop.mvc.model.Purchasing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasingRepository extends JpaRepository<Purchasing, Integer> {

    @Override
    Purchasing save(Purchasing purchasing);

}
