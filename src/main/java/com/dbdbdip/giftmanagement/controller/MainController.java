package com.dbdbdip.giftmanagement.controller;

import com.dbdbdip.giftmanagement.model.dto.CategoryListDTO;
import com.dbdbdip.giftmanagement.model.dto.SubCategoryDTO;
import com.dbdbdip.giftmanagement.service.GiftCEOService;
import com.dbdbdip.giftmanagement.service.MainService;
import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<CategoryListDTO> getCategoryList() {
        return mainService.getCategoryList();
    }


    // 소분류 목록 조회 요청 처리
    @GetMapping("/categories/{categoryId}")
    @ResponseBody
    public List<SubCategoryDTO> getSubCategories(@PathVariable Long categoryId) {
        return mainService.getSubCategories(categoryId);
    }
}
