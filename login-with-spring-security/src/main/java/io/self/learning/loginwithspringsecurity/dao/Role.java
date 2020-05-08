package io.self.learning.loginwithspringsecurity.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Shweta on 4/27/2020.
 */
@Entity @Getter @Setter @NoArgsConstructor @ToString(exclude = "users") public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles") private Set<User> users;

        /*@Override public String toString() {
                return "Role{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }*/
}