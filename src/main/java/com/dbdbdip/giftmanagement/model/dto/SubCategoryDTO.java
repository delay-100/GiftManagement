package com.dbdbdip.giftmanagement.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDTO {
    private Long subCategoryId;
    private String name;
    private Long parentId;

}