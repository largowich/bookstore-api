package com.devalgas.portofolio.bookstore.service;

import com.devalgas.portofolio.bookstore.domain.User;
import com.devalgas.portofolio.bookstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author devalgas kamga on 14/01/2022. 00:35
 * <p>
 * Service Implementation for managing {@link UserDetails}.
 */
@Service
public class UserSecurityService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserSecurityService.class);

    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if (null == user) {
            log.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user;
    }
}
