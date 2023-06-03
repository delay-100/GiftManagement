package com.dbdbdip.giftmanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDate createdAt;

}
