package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GuestbookDto;
import com.example.demo.entity.Guestbook;
import com.example.demo.entity.GuestbookComment;
import com.example.demo.entity.CUser;
import com.example.demo.error.NotFoundException;
import com.example.demo.repository.GuestbookCommentRepository;
import com.example.demo.repository.GuestbookRepository;

@Service
public class GuestbookCommentService {
	@Autowired
	private GuestbookCommentRepository guestbookCommentRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private GuestbookService guestbookService;
	
	public void create(Long guestbookId, GuestbookDto guestbookDto) {
		CUser user = new CUser();
		if (guestbookDto.getRole()!="admin") {
		user = this.userService.checkAndCreateGuest(guestbookDto.getIpAddress());
		}else { user = this.userService.getAdminUser();
		};
		GuestbookComment guestbookComment = new GuestbookComment();
		guestbookComment.setContent(guestbookDto.getContent());
		guestbookComment.setCreateDate(LocalDateTime.now());
		guestbookComment.setUser(user);
		guestbookComment.setGuestbook(this.guestbookService.getOneGuestbookById(guestbookId));
		this.guestbookCommentRepository.save(guestbookComment);
	}
	public GuestbookComment getOneGuestbookCommentById(Long guestbookCommentId) {
		return this.guestbookCommentRepository.findById(guestbookCommentId).orElseThrow(
				() -> new NotFoundException("Not Found GusetbookComment ID"+guestbookCommentId));	
		}
	public void delete(Long guestbookId) {
		this.guestbookCommentRepository.delete(this.getOneGuestbookCommentById(guestbookId));
	}
	public void modify(Long guestbookCommentId, GuestbookDto guestbookDto) {
		GuestbookComment guestbookComment = this.getOneGuestbookCommentById(guestbookCommentId);
		guestbookComment.setContent(guestbookDto.getContent());
		this.guestbookCommentRepository.save(guestbookComment);
	}

}
