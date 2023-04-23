package com.myportfolio.web.controller;

import com.myportfolio.web.domain.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.getAttribute("id");
        session.invalidate();

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserDto userDto, boolean rememberId, Model model, String toURL,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. id와 password를 확인
        if (!loginCheck(userDto)) {
            String msg = URLEncoder.encode("아이디 또는 비밀번호가 일치하지 않습니다.", "utf-8");
            model.addAttribute("msg", msg);

            // 아이디와 비밀번호가 일치하지않으면 로그인 화면으로 이동
            return "redirect:/login/login";
        }

        // 세션 객체 얻어오기
        HttpSession session = request.getSession();
        // 세션 id 저장
        session.setAttribute("id", userDto.getId());

        if (rememberId) {
            // 쿠키 생성
            Cookie cookie = new Cookie("id", userDto.getId());
            response.addCookie(cookie);
        } else {
            // 쿠키 삭제
            Cookie cookie = new Cookie("id", userDto.getId());
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        // 아이디와 비밀번호가 일치하면 홈으로 이동
        toURL = toURL == null || toURL == "" ? "/" : toURL;

        return "redirect:" + toURL;
    }

    private boolean loginCheck(UserDto userDto) {
        return "asdf".equals(userDto.getId()) && "1234".equals(userDto.getPassword());
    }
}
