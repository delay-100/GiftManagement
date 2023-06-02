package com.dbdbdip.giftmanagement.controller;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.dto.GiftPageDTO;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.service.GiftCEOService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/giftceo")
@RequiredArgsConstructor
public class GiftCEOController {

    private final GiftCEOService giftCEOService;

    @GetMapping()
    public String getGiftMain(){
        return "giftCEO/giftCEOMain";
    }

    // ceo가 자신의 gift 보기
    @GetMapping("/list")
    public String getGiftList(Model model, HttpSession session){
        List<GiftDTO> giftList = giftCEOService.getGiftList((String) session.getAttribute("UsersId"));
        model.addAttribute("giftList", giftList);
        return "giftCEO/giftCEOList";
    }


    // ceo가 자신의 gift 추가
    @GetMapping("/new")
    public String getNewGift(){
        return "giftCEO/giftCEOcreate";
    }

    @PostMapping("/new")
    public String createNewGift(@ModelAttribute GiftDTO giftDTO, HttpSession session){
        giftCEOService.createNewGift(giftDTO, (String) session.getAttribute("UsersId"));
        return "redirect:/giftceo/list";
    }


    // ceo가 자신의 gift 검색하기
    @GetMapping("/search")
    public String searchForm() {
        return "giftCEO/search/searchForm";
    }

    @PostMapping("/search")
    public String searchMyGift(GiftDTO giftDTO, Model model, HttpSession session) {
        List<GiftDTO> giftlist = giftCEOService.searchMyGift(giftDTO, (String) session.getAttribute("UsersId"));
        model.addAttribute("giftList",giftlist);
        return "giftCEO/search/searchList";
    }

    @GetMapping("/{giftId}")
    public String getGift(@PathVariable("giftId") Long giftId, HttpSession httpSession, Model model){
        GiftPageDTO giftPageDTO = giftCEOService.getGift(giftId, (String) httpSession.getAttribute("UsersId"));
        model.addAttribute("gift",giftPageDTO);
        return "giftCEO/giftCEO";
    }

    // ceo가 자신의 gift 수정
    @GetMapping("/update/{giftId}")
    public String getUpdateGift(@PathVariable("giftId") Long giftId, HttpSession httpSession,Model model){
        GiftDTO giftDTO = giftCEOService.getUpdateGift(giftId, (String) httpSession.getAttribute("UsersId"));
        model.addAttribute("gift",giftDTO);
        return "giftCEO/giftCEOUpdate";
    }

    @PostMapping("/update/{giftId}")
    public String updateGift(@PathVariable("giftId") Long giftId, @ModelAttribute GiftDTO giftDTO, HttpSession session, Model model){
        GiftDTO giftdto = giftCEOService.updateBoard(giftId, giftDTO, (String) session.getAttribute("UsersId"));
        model.addAttribute("gift",giftDTO);
        return "redirect:/giftceo/" + giftId;
    }

    // ceo가 자신의 gift 삭제
    @DeleteMapping("/delete/{giftId}")
    public String deleteGift(@PathVariable("giftId") Long giftId,HttpSession session){
        giftCEOService.deleteGift(giftId, (String) session.getAttribute("UsersId"));
        return "redirect:/giftceo/list";
    }
}
