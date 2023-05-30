package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftCEOService {

    private final GiftRepository giftRepository;

    @Transactional
    public List<GiftDTO> getGiftList(Users users){
        List<Gift> giftList = giftRepository.findByUserId(users);
        List<GiftDTO> list = new ArrayList<>();

        for(Gift g : giftList) {
            GiftDTO boardDto = GiftDTO.builder()
                    .name(g.getName())
                    .price(g.getPrice())
                    .sales_link(g.getSalesLink())
                    .build();
            list.add(boardDto);
        }

        return list;
    }
    @Transactional
    public void createNewGift(GiftDTO giftDTO, Users users ) {
        System.out.println(users);

        Gift gift = Gift.builder()
                .name(giftDTO.getName()) // 외래 키 값을 가진 객체
                .price(giftDTO.getPrice()) // 외래 키의 닉네임 값
                .salesLink(giftDTO.getSales_link())
                .userId(users)
                .build();

        giftRepository.save(gift);
    }
}
