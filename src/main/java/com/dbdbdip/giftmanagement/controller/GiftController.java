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
public class GiftController { // user, ceo 둘다 가능

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

    // search 구현
    @GetMapping("/search")
    public String searchAllForm() {
        return "gift/search/searchForm";
    }

    // user가 gift 검색하기
    @PostMapping("/search")
    public String searchAllGift(GiftDTO giftDTO, Model model) {
        List<GiftDTO> giftlist = giftService.searchAllGift(giftDTO);
        model.addAttribute("giftList",giftlist);
        return "gift/search/searchList";
    }

    // user가 gift 찜하기

}
