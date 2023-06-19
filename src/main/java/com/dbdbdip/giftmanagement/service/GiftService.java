package com.dbdbdip.giftmanagement.service;

import com.dbdbdip.giftmanagement.model.dto.GiftDTO;
import com.dbdbdip.giftmanagement.model.dto.GiftListDTO;
import com.dbdbdip.giftmanagement.model.dto.GiftPageDTO;
import com.dbdbdip.giftmanagement.model.entity.Category;
import com.dbdbdip.giftmanagement.model.entity.Gift;
import com.dbdbdip.giftmanagement.model.entity.Likes;
import com.dbdbdip.giftmanagement.model.entity.Users;
import com.dbdbdip.giftmanagement.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<GiftListDTO> getGiftList(){
        List<Gift> giftList = giftRepository.findAll();
        List<GiftListDTO> list = new ArrayList<>();

        for(Gift g : giftList) {
            Users user = usersRepository.findByUserId(g.getUserId().getUserId());
            GiftListDTO boardDto = GiftListDTO.builder()
                    .giftId(g.getGiftId())
                    .name(g.getName())
                    .category(g.getCategory().getName())
                    .price(g.getPrice())
                    .sales_link(g.getSalesLink())
                    .ceoNickName(user.getNickname())
                    .ceoUserId(user.getUserId())
                    .build();
            list.add(boardDto);
        }

        return list;
    }

    // user가 gift 전체 검색하기
    @Transactional
    public List<GiftListDTO> searchAllGift(GiftDTO giftDTO){
        List<GiftListDTO> list  = new ArrayList<>();
        Category c = categoryRepository.findByCategoryId(Long.parseLong(giftDTO.getCategory()));

        for (Gift gift : giftRepository.findAllSearch(giftDTO.getName(), c.getName())){
            Users user = usersRepository.findByUserId(gift.getUserId().getUserId());

            GiftListDTO gift2 = GiftListDTO.builder()
                    .giftId(gift.getGiftId())
                    .name(gift.getName())
                    .category(gift.getCategory().getName())
                    .price(gift.getPrice())
                    .sales_link((gift.getSalesLink()))
                    .ceoNickName(user.getNickname())
                    .ceoUserId(user.getUserId())
                    .build();
            list.add(gift2);
        }

        return list;
    }

    public GiftPageDTO getGift(Long giftId, String users){
        Users user = usersRepository.FindByUsersIdToString(users);
        Gift gift = giftRepository.findByGiftId(giftId);
        Likes likes = likesRepository.findByGiftIdAndUserId(gift,user);
        Users giftUser = usersRepository.findByUserId(gift.getUserId().getUserId());
//        if(users.isEmpty()||gift.isEmpty()){
//            return
//        } else {

        if(likes==null) {
            return GiftPageDTO.builder()
                    .giftId(gift.getGiftId())
                    .name(gift.getName())
                    .category(gift.getCategory().getName())
                    .sales_link(gift.getSalesLink())
                    .price(gift.getPrice())
                    .likes(false)
                    .ceoNickName(giftUser.getNickname())
                    .ceoUserId(giftUser.getUserId())
                    .build();
        }
        return GiftPageDTO.builder()
                .giftId(gift.getGiftId())
                .name(gift.getName())
                .category(gift.getCategory().getName())
                .sales_link(gift.getSalesLink())
                .price(gift.getPrice())
                .likes(true)
                .ceoNickName(giftUser.getNickname())
                .ceoUserId(giftUser.getUserId())
                .build();

//        }
    }

    // user가 gift 찜하기/찜 안하기
    public GiftPageDTO likeGift(Long giftId, String users) {
        Users user = usersRepository.FindByUsersIdToString(users);
        Gift gift = giftRepository.findByGiftId(giftId);

//        if(users.isEmpty()||gift.isEmpty()){
//            return
//        } else {

        Likes like = likesRepository.findByGiftIdAndUserId(gift, user);
        if (like==null) {
            Likes likes = Likes.builder()
                    .giftId(gift)
                    .userId(user)
                    .build();

            likesRepository.save(likes);
            return GiftPageDTO.builder()
                    .giftId(gift.getGiftId())
                    .name(gift.getName())
                    .category(gift.getCategory().getName())
                    .sales_link(gift.getSalesLink())
                    .price(gift.getPrice())
                    .likes(true)
                    .build();
        } else {
            likesRepository.deleteById(like.getLikesId());
            return GiftPageDTO.builder()
                    .giftId(gift.getGiftId())
                    .name(gift.getName())
                    .category(gift.getCategory().getName())
                    .sales_link(gift.getSalesLink())
                    .price(gift.getPrice())
                    .likes(false)
                    .build();
        }

//        }
    }
}
