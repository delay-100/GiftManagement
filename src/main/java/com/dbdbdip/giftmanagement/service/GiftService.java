package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftResponse;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {

    private GiftRepository giftRepository;

    public List<GiftResponse> getGiftList(){
        List<GiftResponse> list = null;


        return list;
    }
}
