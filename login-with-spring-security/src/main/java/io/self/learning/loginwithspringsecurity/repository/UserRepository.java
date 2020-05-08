package io.self.learning.loginwithspringsecurity.repository;

import io.self.learning.loginwithspringsecurity.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created in login-with-spring-security.
 */
@Repository public interface UserRepository extends JpaRepository<User, Long> {

        User findByUsername(String username);

        User findByUsernameIgnoreCase(String username);
}