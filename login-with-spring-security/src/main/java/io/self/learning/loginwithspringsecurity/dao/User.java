package io.self.learning.loginwithspringsecurity.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created in login-with-spring-security.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roles")
@Table(name = "app_user") public class User {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

        private String name;

        private String username;

        private String password;

        @Transient private String passwordConfirm;

        @ManyToMany private Set<Role> roles;


/*

        @Override public String toString() {
                return "User{" +
                    "name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }*/
}