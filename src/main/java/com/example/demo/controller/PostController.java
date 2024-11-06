package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDto;
import com.example.demo.entity.Post;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.PostService;


@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService postService;

	@PostMapping("/create")
	public void create(@RequestBody PostDto postDto) {
        System.out.println("Received Board ID: " + postDto.getBoardId());
		this.postService.create(postDto);
	}
	@PostMapping("/{postId}/delete")
	public void delete(@PathVariable("postId") Long postId) {
		this.postService.delete(postId);
	}
	@PostMapping("/{postId}/modify")
	public void modify(@PathVariable("postId") Long postId, @RequestBody PostDto postDto) {
		this.postService.modify(postId, postDto);
	}
    @GetMapping("/board/{boardId}")
    public Page<PostDto> getPostsByBoard(@PathVariable("boardId") Long boardId,
                                      @RequestParam(value="page", defaultValue = "0") int page
//                                      @RequestParam("size") int size // 현재는 10 고정
                                      ) {
    	int size = 10;
        return postService.getPostsByBoardId(boardId, page, size);
    }
    @GetMapping("/board/all")
    public Page<PostDto> getAllPost(@RequestParam(value="page", defaultValue = "0") int page) {
        int size = 10;
        System.out.println("페이지 요청");
        return postService.getAllPosts(page, size);
    }
    @GetMapping("/{postId}")
    public PostDto onePostRead(@PathVariable("postId") Long postId) {
    	return this.postService.postToDto(this.postService.getOnePostById(postId));
    }
}
