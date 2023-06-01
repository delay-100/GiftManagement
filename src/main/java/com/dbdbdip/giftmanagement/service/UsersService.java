package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.UsersForm;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    public void join(UsersForm usersForm) {
        System.out.println("******** 회원가입 서비스 ********");
        Users u = Users.builder()
                .userId(usersForm.getUserId())
                .password(usersForm.getPassword())
                .nickname(usersForm.getNickname())
                .userRole(usersForm.getUserRole())
                .build();

        usersRepository.save(u);
    }
}
