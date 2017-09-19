package com.vkumar.blog.controllers;

import com.vkumar.blog.models.Project;

import com.vkumar.blog.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String projectDashboard(Model model, @RequestParam(value="pid", required=false) Integer pid) {

        return "projects/dashboard";

    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String listProjects(Model model, @RequestParam(value="page", required=false) Integer page){

        if(page == null) page = 0;

        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        Pageable pageable = new PageRequest(page, 5, sort);
        Page<Project> pages = projectRepository.findAll(pageable);


       System.out.println(pages.getNumber());

        //PageWrapper<Project> page = new PageWrapper<Project>(projects, "/products");

        //model.addAttribute("products", page.getContent());
        //model.addAttribute("page", page);
       // System.out.println(projects.);

//        for (Project project : projects.getContent()){
//            System.out.println(project.getName());
//        }

        model.addAttribute("page", pages);
        return "projects/list";
    }


}
