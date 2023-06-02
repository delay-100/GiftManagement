package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Likes;
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
    private final LikesRepository likesRepository;

    public String getNickname(HttpSession httpSession) {
        Users u = usersRepository.findByNickname((String) httpSession.getAttribute("UsersId"));
        return u.getNickname();
    }

    public List<GiftDTO> getMyLikesList(String usersId) {
        Users u = usersRepository.findByUserId(usersId);

        List<Gift> giftList = giftRepository.findByUserIdLikesIdList(usersId);
        List<GiftDTO> giftDTOList = new ArrayList<>();

        for (Gift g : giftList){
            giftDTOList.add(  GiftDTO.builder()
                    .giftId(g.getGiftId())
                    .name(g.getName())
                    .price(g.getPrice())
                    .sales_link(g.getSalesLink())
                    .category(g.getCategory())
                    .build());
        }

        return giftDTOList;
    }
}
