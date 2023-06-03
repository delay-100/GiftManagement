package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.MyPageDTO;
import com.dbdbdip.giftmanagement.model.dto.UsersForm;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Likes;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import com.dbdbdip.giftmanagement.repository.LikesRepository;
import com.dbdbdip.giftmanagement.repository.UsersRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final GiftRepository giftRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public void join(UsersForm usersForm) {
        Users u = Users.builder()
                .userId(usersForm.getUserId())
                .password(usersForm.getPassword())
                .nickname(usersForm.getNickname())
                .userRole(usersForm.getUserRole())
                .build();

        usersRepository.save(u);
    }

    @Transactional
    public boolean idCheck(UsersForm usersForm) {
        Users u = usersRepository.findByUserId(usersForm.getUserId());
        // 입력받은 아이디와 같은 아이디가 없으면 true
        if (u == null) {
            return true;
        }

        // 입력받은 아이디와 같은 아이디가 있으면 false
        return false;
    }

    @Transactional
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
            Users u = usersRepository.findByUserId(usersForm.getUserId());

            httpSession.setAttribute("UsersId", usersForm.getUserId());
            httpSession.setAttribute("UsersPassword", usersForm.getPassword());
            httpSession.setAttribute("UsersRole", u.getUserRole());

            return true;
        }
    }

    @Transactional
    public boolean logout(HttpSession httpSession) {
        if (httpSession != null)
            httpSession.invalidate();
        return true;
    }

//        @Transactional
//    public boolean leave(UsersForm usersForm, String userId) {
//            Users user = usersRepository.findByUserId(userId);
//
//            // 1. 유저와 관련된 좋아요 정보 삭제
//            List<Likes> userLikes = likesRepository.findByUserId(user);
////            for (Likes l : userLikes) {
////                likesRepository.deleteById(l.getLikesId());
////            }
//            likesRepository.deleteAll(userLikes);
//
//            // 2. 유저와 관련된 게시글 정보 삭제
//            List<Gift> userGifts = giftRepository.findByUserIdList(user);
//            for (Gift gift : userGifts) {
//                List<Likes> likeRepo = likesRepository.findByGiftId(gift);
//
//                if (!likeRepo.isEmpty()) {
//                    for(Likes l: likeRepo){
//                        likesRepository.deleteById(l.getLikesId());
//                    }
//                }
//                    gift.setUserId(null);
//                    giftRepository.save(gift);
//
//                    giftRepository.deleteById(gift.getGiftId());
//               }
//
//            // 3. 유저 정보 삭제
//        if(!usersRepository.findByIdAndPassword(usersForm.getPassword(), userId).isEmpty()) {
//            usersRepository.deleteById(userId);
//
//            return true;
//        }
//        else {
//            return false;
//        }
//    }


    @Transactional
    public boolean leave(UsersForm usersForm, String userId) {
            Users user = usersRepository.findByUserId(userId);

            usersRepository.deleteAndCascadeGiftsAndLikes(user);
            return true;

        }


    @Transactional
    public boolean nicknameUpdate(MyPageDTO myPageDTO, HttpSession httpSession) {
        Users u = usersRepository.findByNickname((String) httpSession.getAttribute("UsersId"));
        if (u.getNickname().equals(myPageDTO.getNickname())) {
            // 현재 닉네임과 같으면 false
            return false;
        }

        // 성공적으로 변경되면 true
        usersRepository.updateNickname(u.getUserId(), myPageDTO.getNickname());
        return true;
    }

    @Transactional
    public int passwordUpdate(MyPageDTO myPageDTO, HttpSession httpSession) {
        String currentPassword = (String) httpSession.getAttribute("UsersPassword");
        String newPassword = myPageDTO.getNewPassword();

        if (myPageDTO.getCurrentPassword().compareTo(currentPassword) != 0) {
            // 현재 비밀번호가 올바르지 않으면 1
            return 1;
        }
        else if (newPassword.compareTo(currentPassword) == 0) {
            // 현재 비밀번호와 새 비밀번호가 일치하면 2
            return 2;
        }

        // 현재 비밀번호가 일치하면 3
        // 새 비밀번호로 변경, httpsession 변경
        usersRepository.updatePassword((String) httpSession.getAttribute("UsersId"), myPageDTO.getNewPassword());
        httpSession.removeAttribute("UsersPassword");
        httpSession.setAttribute("UsersPassword", newPassword);

        return 3;
    }
}
