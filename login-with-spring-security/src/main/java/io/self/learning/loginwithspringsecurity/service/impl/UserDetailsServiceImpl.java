package io.self.learning.loginwithspringsecurity.service.impl;

import io.self.learning.loginwithspringsecurity.dao.User;
import io.self.learning.loginwithspringsecurity.repository.UserRepository;
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

/**
 * Created in login-with-spring-security.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired private UserRepository userRepository;

        @Override @Transactional public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                System.out.println("in UserDetailsServiceImpl, username = " + username);
                User user = userRepository.findByUsername(username);
                if (null == user) {
                        throw new UsernameNotFoundException(username);
                }
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                /*for (Role role : user.getRoles()) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
                }*/
                System.out.println("in UserDetailsServiceImpl, user from database = " + user
                    + ", roles = ");
                user.getRoles().stream().forEach(role -> System.out.println(role));

                user.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));

                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
}