package com.vkumar.blog.services;


import com.vkumar.blog.models.User;
import com.vkumar.blog.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //query for user from DB
        User user = userRepository.findByUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Date today = new Date();

        //do check if account expired / suspended / deleted
        Boolean isAcconutExpired = false;
        Boolean status = false;

        if (user.getExpireOn() != null && today.before(user.getExpireOn())) {
            isAcconutExpired = false;
        }


        if(user.getStatus() == 1){
            status = true;
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                status,
                !isAcconutExpired,
                true,
                true,
                getAuthorities(user));

    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
}