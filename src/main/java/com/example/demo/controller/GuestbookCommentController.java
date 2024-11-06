package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GuestbookDto;
import com.example.demo.service.GuestbookService;


@RestController
@RequestMapping("/api/guestbook")
public class GuestbookCommentController {
	@Autowired
	private GuestbookService guestBookService;
	
	@PostMapping("/create")
	public void createComment(@RequestBody GuestbookDto guestbookDto) {
		this.guestBookService.create(guestbookDto);
	}
	@PostMapping("/{guestbookId}/delete")
	public void createComment(@PathVariable Long guestbookId) {
		this.guestBookService.delete(guestbookId);
	}
	@PostMapping("/{postId}/modify")
	public void createComment(@PathVariable Long guestbookId, @RequestBody GuestbookDto guestbookDto) {
		this.guestBookService.modify(guestbookId, guestbookDto);
	}

}
