package io.self.learning.loginwithspringsecurity.service;

/**
 * Created in login-with-spring-security.
 */
public interface SecurityService {
        String findLoggedInUsername();

        void autoLogin(String username, String password);
}

