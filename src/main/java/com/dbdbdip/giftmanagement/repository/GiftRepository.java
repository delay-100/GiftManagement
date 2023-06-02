package com.dbdbdip.giftmanagement.repository;

import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
    Optional<Gift> findById(Long giftId);


    @Query("SELECT g FROM Gift g WHERE g.giftId = :giftId")
    Gift findByGiftId(@Param("giftId") Long giftId);

//    List<Gift> findByUserIdString(String userId);

//    List<Gift> findByUserId(Users userId);

    @Query("SELECT g FROM Gift g WHERE g.userId = :user")
    List<Gift> findByUserIdGiftList(@Param("user") Users user);

//    @Query("select g from Gift g join Users u on g.userId = user where g.name LIKE concat('%', :name, '%')"+
//            " and g.category LIKE concat('%', :category, '%')")
//    List<Gift> findByUserIdGiftIdList(@Param("name") String name, @Param("category") String category,@Param("user") Users user);
@Query("SELECT g FROM Gift g WHERE g.name LIKE CONCAT('%', :name, '%') AND g.category LIKE CONCAT('%', :category, '%') AND g.userId.userId = :userId")
List<Gift> findByUserIdGiftIdList(@Param("name") String name, @Param("category") String category, @Param("userId") String userId);


    @Override
    List<Gift> findAll();

    @Query("select g from Gift g where g.name LIKE concat('%', :name, '%')"+
            " and g.category LIKE concat('%', :category, '%')")
    List<Gift> findAllSearch(@Param("name") String name, @Param("category") String category);
}
