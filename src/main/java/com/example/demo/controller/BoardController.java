package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/delete/{boardId}")
	public void delete(@PathVariable Long boardId) {
	    this.boardService.delete(this.boardService.getOneBoardById(boardId));
	}
	@PostMapping("/create")
	public void create(@RequestBody BoardDto boardDto) {
	    this.boardService.create(boardDto);
	}
	@PostMapping("/modify/{boardId}")
	public void modify(@PathVariable Long boardId, @RequestBody BoardDto boardDto) {
	    this.boardService.modify(boardId, boardDto);
	}
}
