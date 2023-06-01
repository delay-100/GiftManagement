package com.dbdbdip.giftmanagement.repository;


import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Likes;
import com.dbdbdip.giftmanagement.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("SELECT l FROM Likes l WHERE l.giftId = :giftId and l.userId = :users")
    Likes findByGiftIdAndUserId(@Param("giftId") Gift giftId, @Param("users") Users users);
}
