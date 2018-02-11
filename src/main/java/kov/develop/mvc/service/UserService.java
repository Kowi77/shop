package kov.develop.mvc.service;

import kov.develop.mvc.model.User;
import kov.develop.mvc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository repository;


    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public synchronized User save(User user) {
        User user1 = repository.findByUsername(user.getUsername());
        if (user1 == null) {
            return repository.save(user);
        } else {
            return null;
        }

    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User get(Integer integer) {
        return repository.findOne(integer);
    }
}
