package com.vkumar.blog.controllers;

import com.vkumar.blog.models.Blog;
import com.vkumar.blog.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model mode){

        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(0, 3, sort);
        Page<Blog> blogs = blogRepository.findAll(pageable);
        mode.addAttribute("latestPosts", blogs);
        return "home";
    }


}
