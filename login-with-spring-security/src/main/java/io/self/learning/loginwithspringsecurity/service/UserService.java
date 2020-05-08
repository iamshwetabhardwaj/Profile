package io.self.learning.loginwithspringsecurity.service;

import io.self.learning.loginwithspringsecurity.dao.User;

/**
 * Created in login-with-spring-security.
 */
public interface UserService {
        void save(User user);

        User findByUsername(String username);
}
