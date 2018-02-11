package kov.develop.mvc.repository;

import kov.develop.mvc.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    @Override
    User save(User user);

    @Override
    User getOne(Integer integer);

    @Override
    User findOne(Integer integer);

    User findByUsername(String username);
}
