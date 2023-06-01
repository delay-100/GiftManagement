package com.dbdbdip.giftmanagement.controller;


import com.dbdbdip.giftmanagement.model.dto.Message;
import com.dbdbdip.giftmanagement.model.dto.UsersForm;
import com.dbdbdip.giftmanagement.service.UsersService;
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
        return "users/RegisterUsersForm";
    }

    @PostMapping("/signup")
    public ModelAndView createSignup(UsersForm usersForm, ModelAndView mav) {
        System.out.println("******** 회원가입 폼 시작 ********");
        if (usersForm.getUserId().length() == 0) {
            mav.addObject("data", new Message("ID를 입력해주세요.", "signup"));
            mav.setViewName("/common/Message");
        }
        else if (usersForm.getPassword().length() == 0) {
            mav.addObject("data", new Message("비밀번호를 입력해주세요.", "signup"));
            mav.setViewName("/common/Message");
        }
        else if (usersForm.getNickname().length() == 0) {
            mav.addObject("data", new Message("닉네임을 입력해주세요.", "signup"));
            mav.setViewName("/common/Message");
        }
        else if (usersForm.getUserRole() == null) {
            mav.addObject("data", new Message("역할을 선택해주세요.", "signup"));
            mav.setViewName("/common/Message");
        }
        else {
            usersService.join(usersForm);

            mav.addObject("data", new Message("회원가입이 완료되었습니다.", "/"));
            mav.setViewName("/common/Message");
        }

        return mav;
    }

    @GetMapping("/login")
    public String createLoginForm() {
        return "users/LoginUsersForm";
    }

    @PostMapping("/login")
    public String createLogin(UsersForm usersForm) {
        return "redirect:/";
    }
}
