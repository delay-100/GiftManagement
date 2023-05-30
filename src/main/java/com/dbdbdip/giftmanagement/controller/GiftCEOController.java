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
    @GetMapping()
    public String getGift(){
        return "gift/gift";
    }

    @GetMapping("/list")
    public String getGiftList(Model model){
        List<GiftDTO> giftList = giftCEOService.getGiftList();
        model.addAttribute("giftList", giftList);
        return "gift/giftList";
    }

    @GetMapping("/new")
    public String getNewGift(){
        return "gift/createGift.html";
    }

    @PostMapping("/new")
    public String createNewGift(@ModelAttribute GiftDTO giftDTO, HttpSession session){
        giftCEOService.createNewGift(giftDTO, (Users) session.getAttribute("Users"));
        return "gift/gift";
    }
}
