package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.dto.GiftPageDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;
    private final UsersRepository usersRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public List<GiftDTO> getGiftList(){
        List<Gift> giftList = giftRepository.findAll();
        List<GiftDTO> list = new ArrayList<>();

        for(Gift g : giftList) {
            GiftDTO boardDto = GiftDTO.builder()
                    .giftId(g.getGiftId())
                    .name(g.getName())
                    .category(g.getCategory())
                    .price(g.getPrice())
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
                    .giftId(gift.getGiftId())
                    .name(gift.getName())
                    .category(gift.getCategory())
                    .price(gift.getPrice())
                    .sales_link((gift.getSalesLink()))
                    .build();
            list.add(gift2);
        }

        return list;
    }

    public GiftPageDTO getGift(Long giftId, String users){
        Users user = usersRepository.FindByUsersIdToString(users);
        Gift gift = giftRepository.findByGiftId(giftId);
        Likes likes = likesRepository.findByGiftIdAndUserId(gift,user);

//        if(users.isEmpty()||gift.isEmpty()){
//            return
//        } else {

        if(likes==null) {
            return GiftPageDTO.builder()
                    .giftId(gift.getGiftId())
                    .name(gift.getName())
                    .category(gift.getCategory())
                    .sales_link(gift.getSalesLink())
                    .price(gift.getPrice())
                    .likes(false)
                    .build();
        }
        return GiftPageDTO.builder()
                .giftId(gift.getGiftId())
                .name(gift.getName())
                .category(gift.getCategory())
                .sales_link(gift.getSalesLink())
                .price(gift.getPrice())
                .likes(true)
                .build();

//        }
    }

    // user가 gift 찜하기
    public GiftPageDTO likeGift(Long giftId, String users){
        Users user = usersRepository.FindByUsersIdToString(users);
        Gift gift = giftRepository.findByGiftId(giftId);

//        if(users.isEmpty()||gift.isEmpty()){
//            return
//        } else {

        Likes likes = Likes.builder()
                        .giftId(gift)
                        .userId(user)
                .build();

        likesRepository.save(likes);
        return GiftPageDTO.builder()
                .giftId(gift.getGiftId())
                .name(gift.getName())
                .category(gift.getCategory())
                .sales_link(gift.getSalesLink())
                .price(gift.getPrice())
                .likes(true)
                .build();

//        }
    }
}
