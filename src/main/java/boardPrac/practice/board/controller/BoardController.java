package boardPrac.practice.board.controller;

import boardPrac.practice.board.dto.BoardDto;
import boardPrac.practice.board.dto.BoardReplyDto;
import boardPrac.practice.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
        model.addAttribute("title", "게시글 리스트");
        return "board/list.html";
    }

    @GetMapping("/write")
    public String writePage(Model model) {
        model.addAttribute("title", "게시글 작성");
        return "board/write.html";
    }

    @PostMapping("/write")
    public String writeBoard(BoardDto boardDto, HttpServletResponse res) throws Exception {
        String returnUrl = "redirect:/";
        if (boardDto.getContent().isEmpty() || boardDto.getWriter().isEmpty() || boardDto.getTitle().isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('필수값 내용이 누락되었습니다.'); history.go(-1);</script>");
            out.flush();
        } else {
            boardService.saveBoard(boardDto);
        }

        return returnUrl;
    }

    @GetMapping("/view")
    public String view(@RequestParam Long bdId, Model model) {
        BoardDto boardDto = boardService.getBoardId(bdId);
        model.addAttribute("bdView", boardDto);

        List<BoardReplyDto> boardReplyDto = boardService.getReplyBoardId(bdId);
        model.addAttribute("replyList", boardReplyDto);

        model.addAttribute("title", bdId + "번 게시글");
        return "board/view.html";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long bdId, Model model) {
        BoardDto boardDto = boardService.getBoardId(bdId);
        model.addAttribute("bdEdit", boardDto);
        model.addAttribute("title", bdId + "번 게시글 수정");
        return "board/edit.html";
    }

    @PostMapping("/edit")
    public String update(BoardDto boardDto, HttpServletResponse res) throws Exception {
        String returnUrl = "redirect:/";
        if (boardDto.getContent().isEmpty() || boardDto.getTitle().isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('필수값 내용이 누락되었습니다.'); history.go(-1);</script>");
            out.flush();
        } else {
            boardService.updateBoard(boardDto);
        }

        return returnUrl;
    }

    @GetMapping(value = {"/delete"})
    public String deleteList(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList("Y");
        model.addAttribute("delBdList", boardDtoList);
        model.addAttribute("title", "삭제된 게시글 리스트");
        return "board/list.d.html";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long bdId, HttpServletRequest httpServletRequest, HttpServletResponse res) throws Exception {
        String mode = httpServletRequest.getParameter("mode");
        if (Objects.equals(mode, "restore")) {
            mode = "N";
        } else if (Objects.equals(mode, "delete")) {
            mode = "Y";
        } else {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('해당 기능을 수행할 수 없습니다. 다시 시도 해주세요.'); history.go(-1);</script>");
            out.flush();
            return "redirect:/";
        }

        boardService.actionBoard(bdId, mode);

        return "redirect:/";
    }

    @PostMapping("/reply")
    public String replyBoard(BoardReplyDto boardReplyDto, HttpServletRequest httpServletRequest, HttpServletResponse res) throws Exception {
        String returnUrl = "redirect:/view?bdId=" + httpServletRequest.getParameter("id");
        if (boardReplyDto.getContent().isEmpty() || httpServletRequest.getParameter("writer").isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('필수값 내용이 누락되었습니다.'); history.go(-1);</script>");
            out.flush();
        } else {
            boardService.saveReply(boardReplyDto);
        }

        return returnUrl;
    }

    @GetMapping("/replyAction")
    public String replyAction(@RequestParam Long sno, @RequestParam Long id) {
        boardService.actionReply(sno);

        return "redirect:/view?bdId=" + id;
    }
}