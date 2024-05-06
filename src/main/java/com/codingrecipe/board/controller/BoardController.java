package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // controller 지정 - 자동으로 스프링빈 등록(내부에 @component 어노테이션이 있음)
@RequiredArgsConstructor // final 이 붙은 필드의 생성자 생성 - 생성자 주입 방식
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        // 조회수 처리
        boardService.updateHits(id);

        // 상세 내용 가져옴
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
    }
}
