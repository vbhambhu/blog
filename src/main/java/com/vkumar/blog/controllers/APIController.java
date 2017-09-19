package com.vkumar.blog.controllers;


import com.vkumar.blog.models.Blog;
import com.vkumar.blog.models.Project;
import com.vkumar.blog.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

    @Autowired
    BlogRepository blogRepository;

    @ResponseBody
    @RequestMapping(value="/api/blog", method= RequestMethod.GET)
    public Page<Blog> saveSheet(@RequestParam(value="page", required=false) Integer page) {

        if(page == null) page = 0;

        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "title"));
        Pageable pageable = new PageRequest(page, 5, sort);
        Page<Blog> blogs = blogRepository.findAll(pageable);
        return blogs;


    }



}
