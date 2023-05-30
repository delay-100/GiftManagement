package com.dbdbdip.giftmanagement.controller;


import com.dbdbdip.giftmanagement.model.dto.UsersForm;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsersController {

    private UsersService usersService;

    @GetMapping("/signup")
    public String createSignupForm() {
        return "users/RegisterUsersForm";
    }

    @PostMapping("/signup")
    public String createSignup(UsersForm usersForm) {
        System.out.println("******** 회원가입 폼 시작 ********");
        Users users = new Users();
        users.setUserId(usersForm.getId());
        users.setPassword(usersForm.getPassword());
        users.setNickname(usersForm.getNickname());
        users.setUserRole(usersForm.getUserRole());
        usersService.join(users);
        return "redirect:/";
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
