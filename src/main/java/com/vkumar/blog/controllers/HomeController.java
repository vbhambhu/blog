package com.vkumar.blog.controllers;

import com.vkumar.blog.models.Post;
import com.vkumar.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.Normalizer;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

@Controller
public class HomeController {

    @Autowired
    PostRepository postRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model mode){

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<Post> blogs = postRepository.findAll(pageable);
        mode.addAttribute("latestPosts", blogs.getContent());
        return "home";
    }



    @RequestMapping(value = "/post/edit/{slug}", method = RequestMethod.GET)
    public String editPost(@PathVariable("slug") String slug, Model model) {

        Post blog = postRepository.findBySlug(slug);
        model.addAttribute("blog", blog);
        return "post/edit";
    }




    @RequestMapping(value = "/post/add", method = RequestMethod.GET)
    public String addPost(Post post, Model model) {
        return "new";
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public String savePost(@Valid Post blog , BindingResult bindingResult) {



        if (bindingResult.hasErrors()) {
            return "new";
        }



        blog.setSlug(toSlug(blog.getTitle()));
        postRepository.save(blog);


        return "redirect:/";

    }

//    @RequestMapping(value = "/sss", method = RequestMethod.GET)
//    public String showHomes(Model mode){
//
//        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
//        Pageable pageable = new PageRequest(0, 3, sort);
//        Page<Blog> blogs = blogRepository.findAll(pageable);
//        mode.addAttribute("latestPosts", blogs);
//        return "home";
//    }

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    public String showBlog(@PathVariable(name = "slug", required=false) String slug, Model model){


        Post blog = postRepository.findBySlug(slug);

        model.addAttribute("post", blog);

        return "post/view";
    }


    @RequestMapping(value = "tag/{id}", method = RequestMethod.GET)
    public String showTagsPage(Model mode){
        return "post/tagview";
    }



    public String toSlug(String input) {

        Pattern NONLATIN = Pattern.compile("[^\\w-]");
        Pattern WHITESPACE = Pattern.compile("[\\s]");

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }





}
