package io.self.learning.loginwithspringsecurity.service.impl;

import io.self.learning.loginwithspringsecurity.dao.User;
import io.self.learning.loginwithspringsecurity.repository.RoleRepository;
import io.self.learning.loginwithspringsecurity.repository.UserRepository;
import io.self.learning.loginwithspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created in login-with-spring-security.
 */
@Service
public class UserServiceImpl implements UserService {

        @Autowired private UserRepository userRepository;
        @Autowired private RoleRepository roleRepository;
        @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override public void save(User user) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setRoles(new HashSet<>(roleRepository.findAll()));
                userRepository.save(user);

        }

        @Override public User findByUsername(String username) {
                return userRepository.findByUsername(username);
        }
}