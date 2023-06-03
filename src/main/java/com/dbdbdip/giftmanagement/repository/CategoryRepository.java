package com.dbdbdip.giftmanagement.repository;


import com.dbdbdip.giftmanagement.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    @Query("SELECT c FROM Category c where c.parentId = null")
    List<Category> findByParentNull();

    List<Category> findByParentId(Long categoryId);

    Category findByCategoryId(Long categoryId);
}
