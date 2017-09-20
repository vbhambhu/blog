package com.vkumar.blog.repositories;


import com.vkumar.blog.models.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Page<Blog> findAll(Pageable pageable);

    Blog findBySlug(String slug);






}
