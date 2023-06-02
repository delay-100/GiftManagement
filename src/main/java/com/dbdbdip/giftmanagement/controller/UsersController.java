package com.dbdbdip.giftmanagement.controller;


import com.dbdbdip.giftmanagement.model.dto.Message;
import com.dbdbdip.giftmanagement.model.dto.UsersForm;
import com.dbdbdip.giftmanagement.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/signup")
    public String createSignupForm() {
        return "users/registerUsersForm";
    }

    @PostMapping("/signup")
    public ModelAndView createSignup(UsersForm usersForm, ModelAndView mav) {
        if (usersForm.getUserId().length() == 0) {
            mav.addObject("data", new Message("ID를 입력해주세요.", "signup"));
            mav.setViewName("/common/message");
        }
        else if (usersForm.getPassword().length() == 0) {
            mav.addObject("data", new Message("비밀번호를 입력해주세요.", "signup"));
            mav.setViewName("/common/message");
        }
        else if (usersForm.getNickname().length() == 0) {
            mav.addObject("data", new Message("닉네임을 입력해주세요.", "signup"));
            mav.setViewName("/common/message");
        }
        else if (usersForm.getUserRole() == null) {
            mav.addObject("data", new Message("역할을 선택해주세요.", "signup"));
            mav.setViewName("/common/message");
        }
        else {
            usersService.join(usersForm);

            mav.addObject("data", new Message("회원가입이 완료되었습니다.", "login"));
            mav.setViewName("/common/message");
        }

        return mav;
    }

    @GetMapping("/login")
    public String createLoginForm() {
        return "users/loginUsersForm";
    }

    @PostMapping("/login")
    public ModelAndView createLogin(UsersForm usersForm, ModelAndView mav, HttpServletRequest request) {
        boolean isSuccess = usersService.login(usersForm, request);
        if (isSuccess) {
            mav.addObject("data", new Message("로그인 성공.", "../gift"));
            mav.setViewName("/common/message");
        }
        else {
            mav.addObject("data", new Message("ID나 비밀번호를 확인해주세요.", "login"));
            mav.setViewName("/common/message");
        }
        return mav;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logoutGet() {
        return "home";
    }
    @PostMapping("/logout")
    public ModelAndView logoutPost(ModelAndView mav, HttpServletRequest request) {
        if (usersService.logout(request)) {
            mav.addObject("data", new Message("로그아웃 성공.", "../"));
            mav.setViewName("/common/message");
        }
        else {
            mav.addObject("data", new Message("로그아웃 실패.", "../gift"));
            mav.setViewName("/common/message");
        }

        return mav;
    }

    // 탈퇴

    // 비밀번호 변경

    // 닉네임 변경
}
