package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

	@Test
    @Transactional
    public void testGetOneBoardById() {
        Board board = new Board();
        board.setBoardName("공지사항");
        boardRepository.save(board);

        Board foundBoard = boardService.getOneBoardById(board.getId());
        assertNotNull(foundBoard);
        assertEquals("공지사항", foundBoard.getBoardName());
    }

}
