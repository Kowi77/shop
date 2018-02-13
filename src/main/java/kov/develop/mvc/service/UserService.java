package kov.develop.mvc.service;

import kov.develop.mvc.model.User;
import kov.develop.mvc.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kov.develop.mvc.utils.Datalogger.DATA_LOGGER;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Autowired
    public UserRepository repository;

    @Transactional
    public User save(User user) {
        User user1 = repository.findByUsername(user.getUsername());
        if (user1 == null) {
            user1 = repository.save(user);
            DATA_LOGGER.info("User " +  user.getUsername() + " successfully saved");
        } else {
            LOGGER.warn("User saving was failed!");
            user1 = null;
        }
        return user1;
    }

    public User findByUsername(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            LOGGER.warn("User {} not found!", username);
        }
        return user;
    }

    public User get(Integer id) {
        User user = repository.findOne(id);
        if (user == null) {
            LOGGER.warn("User with id {} not found!", id);
        }
        return user;
    }
}
