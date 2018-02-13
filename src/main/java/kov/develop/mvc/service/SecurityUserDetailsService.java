package kov.develop.mvc.service;

import kov.develop.mvc.model.User;
import kov.develop.mvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional(readOnly = true)
@Service("userDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    private static final boolean ENABLED = true;
    private static final boolean NOT_EXPIRED = true;
    private static final boolean NOT_CREDENTIALS_EXPIRED = true;
    private static final boolean NOT_LOCKED = true;

    UserRepository repository;

    @Autowired
    public SecurityUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = repository.findByUsername(username);
            if (user == null) {
                LOGGER.error("User {} not found", username);
                throw new UsernameNotFoundException(username);
            }
            Set<GrantedAuthority> auth = new HashSet<>();
            auth.add(new SimpleGrantedAuthority(user.getRole()));
            LOGGER.info("User {} authority is {}", username, auth.toString());
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), ENABLED, NOT_EXPIRED, NOT_CREDENTIALS_EXPIRED, NOT_LOCKED, auth);
        } catch (Exception e) {
            LOGGER.error("User {} not found", username);
            return null;
        }
    }
}
