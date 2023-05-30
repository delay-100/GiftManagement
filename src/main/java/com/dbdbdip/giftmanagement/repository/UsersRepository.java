package com.dbdbdip.giftmanagement.repository;

import com.dbdbdip.giftmanagement.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {

}