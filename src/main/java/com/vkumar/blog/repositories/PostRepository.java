package com.vkumar.blog.repositories;


import com.vkumar.blog.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    Post findBySlug(String slug);






}
