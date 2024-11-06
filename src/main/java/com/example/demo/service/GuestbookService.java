package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GuestbookDto;
import com.example.demo.entity.Guestbook;
import com.example.demo.entity.CUser;
import com.example.demo.error.NotFoundException;
import com.example.demo.repository.GuestbookRepository;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	@Autowired
	private UserService userService;
	
	public void create(GuestbookDto guestbookDto) {
		CUser user = new CUser();
		if (guestbookDto.getRole()!="admin") {
		user = this.userService.checkAndCreateGuest(guestbookDto.getIpAddress());
		}else { user = this.userService.getAdminUser();
		};
		Guestbook guestbook = new Guestbook();
		guestbook.setContent(guestbookDto.getContent());
		guestbook.setCreateDate(LocalDateTime.now());
		guestbook.setUser(user);
		this.guestbookRepository.save(guestbook);
	}
	public Guestbook getOneGuestbookById(Long guestbookId) {
		return this.guestbookRepository.findById(guestbookId).orElseThrow(
				() -> new NotFoundException("Not Found Gusetbook ID"+guestbookId));	
		}
	public void delete(Long guestbookId) {
		this.guestbookRepository.delete(this.getOneGuestbookById(guestbookId));
	}
	public void modify(Long guestbookId, GuestbookDto guestbookDto) {
		Guestbook guestbook = this.getOneGuestbookById(guestbookId);
		guestbook.setContent(guestbookDto.getContent());
		this.guestbookRepository.save(guestbook);
	}
}
