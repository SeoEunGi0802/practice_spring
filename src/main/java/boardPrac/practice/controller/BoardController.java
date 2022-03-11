package boardPrac.practice.controller;

import boardPrac.practice.dto.BoardDto;
import boardPrac.practice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("bdList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/write")
    public String writePage() {
        return "board/write.html";
    }

    @PostMapping("/write")
    public String writeBoard(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/view")
    public String view(@RequestParam Long bdId, Model model) {
        BoardDto boardDto = boardService.getBoardId(bdId);
        model.addAttribute("bdView", boardDto);
        return "board/view.html";
    }
}