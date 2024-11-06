package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findByBoardId(Long boardId, Pageable pageable);
    Page<Post> findByBoardIdOrderByIdDesc(Long boardId, Pageable pageable); // 최신글부터 보임
    Page<Post> findAllByOrderByIdDesc(Pageable pageable); // 최신글부터 보임

}
