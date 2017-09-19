package com.vkumar.blog.controllers;

import com.vkumar.blog.models.User;
import com.vkumar.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/sss", method = RequestMethod.GET)
    public String showHome(){
        return "redirect:projects";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "account/login";
    }


    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public void setup(){


        User u = new User();
        u.setFirstName("Super");
        u.setLastName("Admin");
        u.setUsername("admin");
        u.setStatus(1);
        u.setEmail("test@gmail.com");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(u);


    }

}
