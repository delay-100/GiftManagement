package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {

    private GiftRepository giftRepository;

    @Transactional
    public List<GiftDTO> getGiftList(){
        List<Gift> giftList = giftRepository.findAll();
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
}
