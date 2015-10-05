/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beeva.spring.security;

import com.beeva.mongodb.service.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 *
 */
public class MongoUserDetailsService implements UserDetailsService {


    private static final Logger logger = Logger.getLogger(MongoUserDetailsService.class);

    private DataService dataService;

    @Autowired
    public MongoUserDetailsService (DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        com.beeva.mongodb.model.User user = getUserDetail(email);

        return new User(user.getEmail(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user.getRole()));
    }

    public List<GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = new ArrayList<>();
        if (role.intValue() == 1) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else if (role.intValue() == 2) {
            authList.add(new SimpleGrantedAuthority("ROLE_FORUM"));
        }
        return authList;
    }

    public com.beeva.mongodb.model.User getUserDetail(String email) {

        com.beeva.mongodb.model.User user = dataService.loadUserByEmail(email);
        return user;
    }
}