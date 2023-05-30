package com.dbdbdip.giftmanagement.controller;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.service.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gift")
@RequiredArgsConstructor
public class GiftController {

    private final GiftService giftService;
    
    // user의 gift 화면
    @GetMapping()
    public String getGift(){
        return "gift/gift";
    }

    // user가 전체 gift 리스트 보기
    @GetMapping("/list")
    public String getGiftList(Model model){
        List<GiftDTO> giftList = giftService.getGiftList();
        model.addAttribute("giftList", giftList);
        return "gift/giftList";
    }

    // user가 gift 목록보기

    // user가 gift 검색하기
}
