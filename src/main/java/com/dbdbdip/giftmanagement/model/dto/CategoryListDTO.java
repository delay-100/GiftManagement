package com.dbdbdip.giftmanagement.model.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDTO {
    private Long categoryId;
    private String name;
    private List<SubCategoryDTO> subCategories;

}