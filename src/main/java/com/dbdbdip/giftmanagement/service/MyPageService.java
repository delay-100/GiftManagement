package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.dto.GiftListDTO;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Likes;
import com.dbdbdip.giftmanagement.model.entity.Role;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import com.dbdbdip.giftmanagement.repository.LikesRepository;
import com.dbdbdip.giftmanagement.repository.UsersRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UsersRepository usersRepository;
    private final GiftRepository giftRepository;

    public String getNickname(HttpSession httpSession) {
        Users u = usersRepository.findByNickname((String) httpSession.getAttribute("UsersId"));
        return u.getNickname();
    }

    public List<GiftListDTO> getMyLikesList(String usersId) {
        Users u = usersRepository.findByUserId(usersId);

        List<Gift> giftList = giftRepository.findByUserIdLikesIdList(usersId);
        List<GiftListDTO> giftDTOList = new ArrayList<>();

        for (Gift g : giftList){
            Users user = usersRepository.findByUserId(g.getUserId().getUserId());
            giftDTOList.add(  GiftListDTO.builder()
                    .giftId(g.getGiftId())
                    .name(g.getName())
                    .price(g.getPrice())
                    .sales_link(g.getSalesLink())
                    .category(g.getCategory().getName())
                    .ceoUserId(user.getUserId())
                    .ceoNickName(user.getNickname())
                    .build());
        }

        return giftDTOList;
    }

    public String getUsersRole(HttpSession httpSession) {
        Users u = usersRepository.findByUserId((String) httpSession.getAttribute("UsersId"));
        return u.getUserRole().toString();
    }
}
