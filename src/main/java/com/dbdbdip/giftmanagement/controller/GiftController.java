package com.dbdbdip.giftmanagement.controller;

import com.dbdbdip.giftmanagement.model.dto.GiftResponse;
import com.dbdbdip.giftmanagement.service.GiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gift")
@RequiredArgsConstructor
public class GiftController {

    private final GiftService giftService;

    @GetMapping("/list")
    public String getGiftList(){
        List<GiftResponse> giftList = giftService.getGiftList();
        System.out.println("---aa");
        return "gift/giftList.html";
    }

    @PostMapping("/new")
    public String createGift(){

        return "gift/createGift.html";
    }
}
