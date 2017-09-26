package com.vkumar.blog.controllers;


import com.vkumar.blog.models.Post;
import com.vkumar.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

    @Autowired
    PostRepository blogRepository;

    @ResponseBody
    @RequestMapping(value="/api/blog", method= RequestMethod.GET)
    public Page<Post> saveSheet(@RequestParam(value="page", required=false) Integer page) {

        if(page == null) page = 0;

        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "title"));
        Pageable pageable = new PageRequest(page, 5, sort);
        Page<Post> blogs = blogRepository.findAll(pageable);
        return blogs;


    }



}
