package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Post;
import com.example.demo.error.NotFoundException;
import com.example.demo.repository.BoardRepository;


@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public void create(BoardDto boardDto) {
		Board board = new Board();
		board.setBoardName(boardDto.getBoardName());
		this.boardRepository.save(board);
	}
	public void delete(Board board) {
		this.boardRepository.delete(board);
	}
	public void modify(Long boardId, BoardDto boardDto) {
		Board board = this.getOneBoardById(boardId);
		board.setBoardName(boardDto.getBoardName());
		this.boardRepository.save(board);
	}
	@Transactional
	public Board getOneBoardById(Long boardId) {
		return this.boardRepository.findById(boardId).orElseThrow(
				() -> new NotFoundException("Not Found Board ID "+boardId));
	}
}
