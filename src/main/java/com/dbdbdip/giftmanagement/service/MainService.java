package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.CategoryListDTO;
import com.dbdbdip.giftmanagement.model.dto.SubCategoryDTO;
import com.dbdbdip.giftmanagement.model.entity.Category;
import com.dbdbdip.giftmanagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainService {
    private final CategoryRepository categoryRepository;

    public List<CategoryListDTO> getCategoryList() {
        List<Category> categories = categoryRepository.findByParentNull();
        return categories.stream()
                .map(this::convertToCategoryListDTO)
                .collect(Collectors.toList());
    }

    private CategoryListDTO convertToCategoryListDTO(Category category) {
        return CategoryListDTO.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .build();
    }

    // 소분류 목록 조회
    public List<SubCategoryDTO> getSubCategories(Long categoryId) {
        List<Category> subCategories = categoryRepository.findByParentId(categoryId);
        return subCategories.stream()
                .map(this::convertToSubCategoryDTO)
                .collect(Collectors.toList());
    }

    // Category를 SubCategoryDTO로 변환
    private SubCategoryDTO convertToSubCategoryDTO(Category category) {
        return SubCategoryDTO.builder()
                .subCategoryId(category.getCategoryId())
                .name(category.getName())
                .build();
    }

}
