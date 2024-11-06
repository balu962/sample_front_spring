package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.GuestbookComment;

public interface GuestbookCommentRepository extends JpaRepository<GuestbookComment, Long> {

}
