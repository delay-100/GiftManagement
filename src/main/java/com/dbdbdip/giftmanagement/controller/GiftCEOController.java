package com.dbdbdip.giftmanagement.controller;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.service.GiftCEOService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/giftceo")
@RequiredArgsConstructor
public class GiftCEOController {

    private final GiftCEOService giftCEOService;

    // ceo가 자신의 gift 보기
    @GetMapping("/list")
    public String getGiftList(Model model, HttpSession session){
        List<GiftDTO> giftList = giftCEOService.getGiftList((Users) session.getAttribute("Users"));
        model.addAttribute("giftList", giftList);
        return "gift/giftCEOList";
    }


    // ceo가 자신의 gift 추가
    @GetMapping("/new")
    public String getNewGift(){
        return "gift/createGift.html";
    }

    @PostMapping("/new")
    public String createNewGift(@ModelAttribute GiftDTO giftDTO, HttpSession session){
        giftCEOService.createNewGift(giftDTO, (Users) session.getAttribute("Users"));
        return "gift/gift";
    }

    // ceo가 자신의 gift 수정

    // ceo가 자신의 gift 삭제

    // ceo가 자신의 gift 검색
}
