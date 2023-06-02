package com.dbdbdip.giftmanagement.controller;


import com.dbdbdip.giftmanagement.model.dto.Message;
import com.dbdbdip.giftmanagement.model.dto.MyPageDTO;
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
    public ModelAndView createLogin(UsersForm usersForm, ModelAndView mav, HttpSession httpSession) {
        boolean isSuccess = usersService.login(usersForm, httpSession);
        if (isSuccess) {
            mav.addObject("data", new Message("로그인 성공", "../gift"));
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
    public ModelAndView logoutPost(ModelAndView mav, HttpSession httpSession) {
        if (usersService.logout(httpSession)) {
            mav.addObject("data", new Message("로그아웃 성공", "../"));
            mav.setViewName("/common/message");
        }
        else {
            mav.addObject("data", new Message("로그아웃 실패", "../gift"));
            mav.setViewName("/common/message");
        }

        return mav;
    }

    // 탈퇴
    @GetMapping("/leave")
    public String leaveGet() { return "users/leaveUsersForm"; }

    @DeleteMapping("/leave")
    public ModelAndView leaveDelete(UsersForm usersForm, ModelAndView mav, HttpSession httpSession) {
        if (usersService.leave(usersForm, httpSession)){
            mav.addObject("data", new Message("탈퇴가 완료되었습니다.", "../"));
            mav.setViewName("/common/message");
        }
        else {
            mav.addObject("data", new Message("비밀번호가 올바르지 않습니다.", "/auth/leave"));
            mav.setViewName("/common/message");
        }

        return mav;
    }

    // 닉네임 변경
    @PatchMapping("/nicknameUpdate")
    public ModelAndView nicknameUpdate(MyPageDTO myPageDTO, HttpSession httpSession, ModelAndView mav) {
        // 현재 닉네임과 같으면 false
        // 성공적으로 변경되면 true
        if (usersService.nicknameUpdate(myPageDTO, httpSession)) {
            mav.addObject("data", new Message("닉네임이 변경되었습니다.", "/mypage"));
            mav.setViewName("/common/message");
        }
        else {
            mav.addObject("data", new Message("현재 닉네임과 똑같습니다.", "/mypage"));
            mav.setViewName("/common/message");
        }

        return mav;
    }

    // 비밀번호 변경
    @PatchMapping("/passwordUpdate")
    public ModelAndView passwordUpdate(MyPageDTO myPageDTO, HttpSession httpSession, ModelAndView mav) {

        switch (usersService.passwordUpdate(myPageDTO, httpSession)) {
            case 1 :
                // 현재 비밀번호가 올바르지 않으면 1 X
                mav.addObject("data", new Message("현재 비밀번호가 올바르지 않습니다.", "/mypage"));
                mav.setViewName("/common/message");
                break;
            case 2 :
                // 현재 비밀번호와 새 비밀번호가 일치하면 2 O
                mav.addObject("data", new Message("현재 비밀번호와 새 비밀번호가 일치합니다.", "/mypage"));
                mav.setViewName("/common/message");
                break;
            case 3 :
                // 현재 비밀번호가 일치하면 3 X
                mav.addObject("data", new Message("비밀번호가 변경되었습니다.", "/mypage"));
                mav.setViewName("/common/message");
                break;
        }

        return mav;
    }
}
