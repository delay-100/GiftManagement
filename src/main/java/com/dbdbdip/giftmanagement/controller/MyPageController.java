package com.dbdbdip.giftmanagement.controller;

import com.dbdbdip.giftmanagement.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping()
    public String getMypage(Model model, HttpSession httpSession) {
        model.addAttribute("userNickname", myPageService.getNickname(httpSession));
        model.addAttribute("giftList", myPageService.getMyLikesList((String)httpSession.getAttribute("UsersId")));
        model.addAttribute("usersRole", myPageService.getUsersRole(httpSession));
        return "mypage/myPage";
    }
}
