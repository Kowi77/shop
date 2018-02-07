package kov.develop.mvc.service;

import kov.develop.mvc.model.User;
import kov.develop.mvc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository repository;


    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User get(Integer integer) {
        return repository.findOne(integer);
    }
}
