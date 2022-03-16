package boardPrac.practice.member.controller;

import boardPrac.practice.member.dto.MemberDto;
import boardPrac.practice.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("title", "회원가입");
        return "member/join.html";
    }

    @PostMapping("/join")
    public String save(MemberDto memberDto, HttpServletResponse res) throws Exception {
        String returnUrl = "redirect:/";

        if (memberDto.getId().isEmpty() || memberDto.getPwd().isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('필수값 내용이 누락되었습니다.'); history.go(-1);</script>");
            out.flush();
        } else if (memberDto.getId().length() > 20 || memberDto.getPwd().length() > 20) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('최대 글자수를 확인해주세요.'); history.go(-1);</script>");
            out.flush();
        } else {
            memberService.saveMember(memberDto);
        }

        return returnUrl;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "로그인");
        return "member/login.html";
    }

    @RequestMapping("/loginAction")
    public String login(MemberDto memberDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        String returnUrl = "redirect:/";
        if (memberDto.getId().isEmpty() || memberDto.getPwd().isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('필수값 내용이 누락되었습니다.'); history.go(-1);</script>");
            out.flush();
        } else {
            HashMap memberInfo = memberService.loginAction(memberDto);
            if ((boolean) memberInfo.get("flag")) {
                HttpSession session = req.getSession();
                session.setAttribute("memberInfo", memberInfo.get("data"));
            } else {
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>alert('입력정보가 일치하지 않습니다.'); history.go(-1);</script>");
                out.flush();
            }
        }

        return returnUrl;
    }
}
