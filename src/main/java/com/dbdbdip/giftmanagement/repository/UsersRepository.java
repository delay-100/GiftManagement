package com.dbdbdip.giftmanagement.repository;

import com.dbdbdip.giftmanagement.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("select u from Users u where u.password = :password and u.userId = :userId")
    Optional<Users> findByIdAndPassword(@Param("password") String password, @Param("userId") String userId);

    @Query("SELECT u FROM Users u WHERE u.userId = :users")
    Users FindByUsersIdToString(@Param("users")String users);

    @Query("SELECT u FROM Users u WHERE u.userId = :id")
    Users findByNickname(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.nickname = :nickname WHERE u.userId = :userId")
    void updateNickname(@Param("userId") String userId, @Param("nickname") String nickname);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.password = :password WHERE u.userId = :userId")
    void updatePassword(@Param("userId") String userId, @Param("password") String password);

}