package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.UsersForm;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private UsersRepository usersRepository;
    public String join(Users users) {
        System.out.println("******** 회원가입 서비스 ********");
        System.out.println("usersID: " + users.getUserId());
        System.out.println("usersPassword: " + users.getPassword());
        System.out.println("usersNickname: " + users.getNickname());
        System.out.println("userRole: " + users.getUserRole());
        usersRepository.save(users);
        return users.getUserId();
    }
}
