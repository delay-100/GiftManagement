package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.UsersForm;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Likes;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import com.dbdbdip.giftmanagement.repository.LikesRepository;
import com.dbdbdip.giftmanagement.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final LikesRepository likesRepository;
    private final GiftRepository giftRepository;
    public void join(UsersForm usersForm) {
        Users u = Users.builder()
                .userId(usersForm.getUserId())
                .password(usersForm.getPassword())
                .nickname(usersForm.getNickname())
                .userRole(usersForm.getUserRole())
                .build();

        usersRepository.save(u);
    }

    public boolean login(UsersForm usersForm, HttpSession httpSession) {
        if (usersForm.getUserId().length() == 0 || usersForm.getPassword().length() == 0) {
            // 아이디를 입력하지 않았거나 비밀번호를 입력하지 않은 경우
            return false;
        }
        else if (usersRepository.findById(usersForm.getUserId()).isEmpty()){
            // 아이디를 비교했을 때, 저장되지 않은 아이디일 경우
            return false;
        }
        else if (usersRepository.findByIdAndPassword(usersForm.getPassword(), usersForm.getUserId()).isEmpty()){
            // 아이디와 패스워드를 비교했을 때, 짝이 맞지 않는 경우
            return false;
        }
        else{
            httpSession.setAttribute("UsersId", usersForm.getUserId());
            httpSession.setAttribute("UsersPassword", usersForm.getPassword());

            return true;
        }
    }

    public boolean logout(HttpSession httpSession) {
        if (httpSession != null)
            httpSession.invalidate();

        return true;
    }

    public boolean leave(UsersForm usersForm, HttpSession httpSession) {
        Users u = new Users();
        u.setUserId((String) httpSession.getAttribute("UsersId"));
        if(usersRepository.findByIdAndPassword(usersForm.getPassword(), u.getUserId()).isEmpty()) {
            // 지금 로그인된 아이디랑 입력된 비밀번호가 같지 않으면
            return false;
        }

        usersRepository.deleteById(u.getUserId());
        return true;
    }
}
