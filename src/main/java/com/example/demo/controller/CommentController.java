package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDto;
import com.example.demo.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/post/{postId}")
	public void createComment(@PathVariable("postId") Long postId, @RequestBody CommentDto commentDto) {
		this.commentService.create(postId, commentDto);
	}
	@PostMapping("/delete/{commentId}")
	public void deleteComment(@PathVariable("commentId") Long commentId) {
		this.commentService.delete(commentId);
	}
	@PostMapping("/modify/{commentId}")
	public void modify(@PathVariable("commentId") Long commentId,
			@RequestBody CommentDto commentDto) {
		this.commentService.modify(commentId, commentDto);
	}
	@GetMapping("/list/post/{postId}")
	public List<CommentDto> modify(@PathVariable("postId") Long postId) {
		return this.commentService.getCommentsByPostId(postId);
	}
	

}
