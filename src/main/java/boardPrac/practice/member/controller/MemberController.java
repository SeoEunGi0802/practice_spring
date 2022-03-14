package boardPrac.practice.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "로그인");
        return "member/login.html";
    }

    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("title", "회원가입");
        return "member/join.html";
    }
}
