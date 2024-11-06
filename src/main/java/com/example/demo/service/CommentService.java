package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.entity.BComment;
import com.example.demo.entity.Post;
import com.example.demo.entity.CUser;
import com.example.demo.error.NotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	
	// 댓글 생성
	public void create(Long postId, CommentDto commentDto) {
		CUser user = new CUser();
		if (commentDto.getRole()!="admin") {
		user = this.userService.checkAndCreateGuest(commentDto.getIpAddress());
		}else { user = this.userService.getAdminUser();
		};
		BComment comment = new BComment();
		comment.setContent(commentDto.getContent());
		comment.setCreateDate(LocalDateTime.now());
		comment.setUser(user);
		comment.setNickname(commentDto.getNickname());
		comment.setIpAddress(commentDto.getIpAddress());
		comment.setTemporaryPassword(commentDto.getTemporaryPassword());
		comment.setRole(commentDto.getRole());
		comment.setPost(this.postService.getOnePostById(postId));
		this.commentRepository.save(comment);
	}
	// 해당 댓글 1개 찾기
	public BComment getOneCommentById(Long commentId) {
		return this.commentRepository.findById(commentId).orElseThrow(
				() -> new NotFoundException("Not Found Comment ID "+commentId));	
	}
    // 댓글 삭제
	public void delete(Long commentId) {
		this.commentRepository.delete(this.getOneCommentById(commentId));
	}
	// 수정
	public void modify(Long commentId, CommentDto commentDto) {
		BComment comment = this.getOneCommentById(commentId);
		comment.setContent(commentDto.getContent());
		this.commentRepository.save(comment);
	}
	// dto로 변경
    public CommentDto commentToDto(BComment comment) {
    	CommentDto commentDto = new CommentDto();
    	commentDto.setContent(comment.getContent());
    	commentDto.setCreateDate(comment.getCreateDate());
    	commentDto.setId(comment.getId());
    	commentDto.setIpAddress(comment.getIpAddress());
    	commentDto.setNickname(comment.getNickname());
    	commentDto.setRole(comment.getRole());
    	commentDto.setTemporaryPassword(comment.getTemporaryPassword());
        return commentDto;
    }
    // 게시물 댓글 목록
	public List<CommentDto> getCommentsByPostId(Long postId) {
		List<BComment> comments = this.commentRepository.findByPostIdOrderByIdAsc(postId);
        return comments.stream().map(this::commentToDto).toList();
    }

}
