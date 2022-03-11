package boardPrac.practice.controller;

import boardPrac.practice.dto.BoardDto;
import boardPrac.practice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
        String ip = getClientIp();

        BoardDto boardDto = boardService.getBoardId(bdId, ip);
        model.addAttribute("bdView", boardDto);
        return "board/view.html";
    }

    public static String getClientIp() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = req.getHeader("X-FORWARDED-FOR");

        if (ip == null) {
            ip = req.getHeader("Proxy-Client-IP");
        }

        if (ip == null) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null) {
            ip = req.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null) {
            ip = req.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null) {
            ip = req.getRemoteAddr();
        }

        if (ip == "0:0:0:0:0:0:0:1") {
            ip = "127.0.0.1";
        }

        return ip;
    }
}