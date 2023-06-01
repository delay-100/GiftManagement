package com.dbdbdip.giftmanagement.repository;

import com.dbdbdip.giftmanagement.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("select u from Users u where u.password = :password and u.userId = :userId")
    Optional<Users> findByIdAndPassword(@Param("password") String password, @Param("userId") String userId);

}