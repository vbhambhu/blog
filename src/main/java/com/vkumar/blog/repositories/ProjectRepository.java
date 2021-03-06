package com.vkumar.blog.repositories;

import com.vkumar.blog.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

    Page<Project> findAll(Pageable pageable);


   // Project findById(Long id);

}
