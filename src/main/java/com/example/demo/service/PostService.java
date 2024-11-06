package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.GuestbookDto;
import com.example.demo.dto.PostDto;
import com.example.demo.entity.Guestbook;
import com.example.demo.entity.Post;
import com.example.demo.error.NotFoundException;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private BoardService boardService;
	
	// 생성
	public void create(PostDto postDto) {
        System.out.println("Received Board ID: " +  postDto.getBoardId());
        System.out.println(this.boardService.getOneBoardById(1L));
		Post post = new Post();
		post.setBoard(this.boardService.getOneBoardById(postDto.getBoardId()));
		post.setContent(postDto.getContent());
		post.setCreateDate(LocalDateTime.now());
		post.setTitle(postDto.getTitle());
		this.postRepository.save(post);
	}
	// 글 획득
	public Post getOnePostById(Long postId) {
		return this.postRepository.findById(postId).orElseThrow(
				() -> new NotFoundException("Not Found Post ID"+postId));	
	}
	// 삭제
	public void delete(Long postId) {
		this.postRepository.delete(this.getOnePostById(postId));
	}
	// 수정
	public void modify(Long postId, PostDto postDto) {
		Post post = this.getOnePostById(postId);
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		this.postRepository.save(post);
	}
	// dto로 변경
    public PostDto postToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setBoardId(post.getBoard().getId());
        postDto.setCreateDate(post.getCreateDate());
        postDto.setId(post.getId());
        return postDto;
    }
    
    // 게시판 별 조회
	public Page<PostDto> getPostsByBoardId(Long boardId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findByBoardIdOrderByIdDesc(boardId, pageable);
        return posts.map(this::postToDto);
    }
	// 모든 게시판 조회
	public Page<PostDto> getAllPosts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAllByOrderByIdDesc(pageable);
        System.out.println(posts);
        return posts.map(this::postToDto);
	}
}
