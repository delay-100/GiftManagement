package com.dbdbdip.giftmanagement.repository;


import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Likes;
import com.dbdbdip.giftmanagement.model.entity.Users;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("SELECT l FROM Likes l WHERE l.giftId = :giftId and l.userId = :users")
    Likes findByGiftIdAndUserId(@Param("giftId") Gift giftId, @Param("users") Users users);

    @Modifying
    @Query("DELETE FROM Likes l WHERE l.likesId = :likesId")
    void deleteById(@Param("likesId") Long likesId);

    @Modifying
    @Query("DELETE FROM Likes l WHERE l.giftId = :giftId")
    void deleteByGiftId(@Param("giftId") Gift giftId);

    @Query("SELECT l FROM Likes l WHERE l.giftId = :giftId")
    List<Likes> findByGiftId(@Param("giftId") Gift giftId);


    @Query("SELECT l FROM Likes l WHERE l.userId = :users")
    List<Likes> findByUserId(@Param("users") Users users);
}

