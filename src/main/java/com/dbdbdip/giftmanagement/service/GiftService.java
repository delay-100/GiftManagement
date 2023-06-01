package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import com.dbdbdip.giftmanagement.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;

    @Transactional
    public List<GiftDTO> getGiftList(){
        List<Gift> giftList = giftRepository.findAll();
        List<GiftDTO> list = new ArrayList<>();

        for(Gift g : giftList) {
            GiftDTO boardDto = GiftDTO.builder()
                    .name(g.getName())
                    .price(g.getPrice())
                    .category(g.getCategory())
                    .sales_link(g.getSalesLink())
                    .build();
            list.add(boardDto);
        }

        return list;
    }

    // user가 gift 전체 검색하기
    @Transactional
    public List<GiftDTO> searchAllGift(GiftDTO giftDTO){
        List<GiftDTO> list  = new ArrayList<>();
        for (Gift gift : giftRepository.findAllSearch(giftDTO.getName(), giftDTO.getCategory())){
            GiftDTO gift2 = GiftDTO.builder()
                    .name(gift.getName())
                    .category(gift.getCategory())
                    .price(gift.getPrice())
                    .sales_link((gift.getSalesLink()))
                    .build();
            list.add(gift2);
        }

        return list;
    }

    // user가 gift 찜하기

}
