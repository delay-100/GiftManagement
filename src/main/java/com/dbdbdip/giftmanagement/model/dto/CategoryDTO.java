package com.dbdbdip.giftmanagement.model.dto;

import com.dbdbdip.giftmanagement.model.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private Category parentCategory;
    private String name;
    private LocalDate createdAt;

}
