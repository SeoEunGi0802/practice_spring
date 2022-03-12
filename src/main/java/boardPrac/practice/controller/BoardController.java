package boardPrac.practice.controller;

import boardPrac.practice.dto.BoardDto;
import boardPrac.practice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = {"/", "/index", "/main", "/list"})
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList("N");
        model.addAttribute("bdList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/write")
    public String writePage() {
        return "board/write.html";
    }

    @PostMapping("/write")
    public String writeBoard(BoardDto boardDto) {
        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    @GetMapping("/view")
    public String view(@RequestParam Long bdId, Model model) {
        BoardDto boardDto = boardService.getBoardId(bdId);
        model.addAttribute("bdView", boardDto);
        return "board/view.html";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long bdId, Model model) {
        BoardDto boardDto = boardService.getBoardId(bdId);
        model.addAttribute("bdEdit", boardDto);
        return "board/edit.html";
    }

    @PostMapping("/edit")
    public String update(BoardDto boardDto) {
        boardService.updateBoard(boardDto);
        return "redirect:/";
    }

    @GetMapping(value = {"/delete"})
    public String deleteList(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList("Y");
        model.addAttribute("delBdList", boardDtoList);
        return "board/list.d.html";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long bdId, HttpServletRequest httpServletRequest) {
        String mode = httpServletRequest.getParameter("mode");
        if (Objects.equals(mode, "restore")) {
            mode = "N";
        } else if (Objects.equals(mode, "delete")) {
            mode = "Y";
        }

        boardService.actionBoard(bdId, mode);

        return "redirect:/";
    }
}