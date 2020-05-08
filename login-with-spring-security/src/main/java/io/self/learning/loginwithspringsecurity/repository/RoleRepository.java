package io.self.learning.loginwithspringsecurity.repository;

import io.self.learning.loginwithspringsecurity.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created in login-with-spring-security.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
