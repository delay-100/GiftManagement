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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftCEOService {

    private final GiftRepository giftRepository;
    private final UsersRepository usersRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public List<GiftDTO> getGiftList(String usersId){
        Users user = usersRepository.FindByUsersIdToString(usersId);
        List<Gift> giftList = giftRepository.findByUserIdGiftList(user);
        List<GiftDTO> list = new ArrayList<>();

        for(Gift g : giftList) {
            GiftDTO gift = GiftDTO.builder()
                    .giftId(g.getGiftId())
                    .name(g.getName())
                    .category(g.getCategory())
                    .price(g.getPrice())
                    .sales_link(g.getSalesLink())
                    .build();
            list.add(gift);
        }

        return list;
    }
    @Transactional
    public void createNewGift(GiftDTO giftDTO,String usersId) {
        Users user = usersRepository.FindByUsersIdToString(usersId);

        Gift gift = Gift.builder()
                .name(giftDTO.getName())
                .category(giftDTO.getCategory())
                .price(giftDTO.getPrice())
                .salesLink(giftDTO.getSales_link())
                .userId(user)
                .build();

        giftRepository.save(gift);
    }

    // ceo가 자신의 gift 검색하기
    @Transactional
    public List<GiftDTO> searchMyGift(GiftDTO giftDTO, String usersId){
        Users user = usersRepository.FindByUsersIdToString(usersId);
        List<Gift> gRepo = giftRepository.findByUserIdGiftIdList(giftDTO.getName(),giftDTO.getCategory(),usersId);

        System.out.println(gRepo);
        List<GiftDTO> list  = new ArrayList<>();
        for (Gift gift : gRepo){
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

//        if(users.isEmpty()||gift.isEmpty()){
//            return
//        } else {

        return GiftPageDTO.builder()
                .giftId(gift.getGiftId())
                .name(gift.getName())
                .category(gift.getCategory())
                .sales_link(gift.getSalesLink())
                .price(gift.getPrice())
                .build();

    }

    // UPDATE GIFT

    @Transactional
    public GiftDTO getUpdateGift(Long giftId, String userId){
        Users user = usersRepository.findByUserId(userId);
        Gift gift = giftRepository.findByGiftIdUserId(giftId,user);
        return GiftDTO.builder()
                .giftId(gift.getGiftId())
                .name(gift.getName())
                .category(gift.getCategory())
                .price(gift.getPrice())
                .sales_link(gift.getSalesLink())
                .build();
    }

    @Transactional
    public GiftDTO updateBoard(Long giftId, GiftDTO giftDTO, String userId) {
        Optional<Gift> giftOptional = giftRepository.findById(giftId);
        if(giftOptional.isPresent()) {
            Gift gift = giftOptional.get();
            // board의 작성자 userId랑 요청한 userId랑 같은 경우 수정 가능
            if (userId.equals(gift.getUserId().getUserId())) {
                gift.setName(giftDTO.getName());
                gift.setCategory(giftDTO.getCategory());
                gift.setPrice(giftDTO.getPrice());
                gift.setSalesLink(giftDTO.getSales_link());
                // media 추가해야함
                return GiftDTO
                        .builder()
                        .giftId(gift.getGiftId())
                        .name(gift.getName())
                        .price(gift.getPrice())
                        .sales_link(gift.getSalesLink())
                        .build();
            } else {
                throw new RuntimeException("자신의 선물만 수정가능합니다.");
            }
        }
        else {
            throw new RuntimeException("선물 정보가 없습니다.");
        }
    }
    // DELETE GIFT
    @Transactional
    public boolean deleteGift(Long giftId , String userId) {
        if(giftRepository.findByGiftId(giftId).getUserId().getUserId().equals(userId)) {
            giftRepository.deleteById(giftId);
            return true;
        }

        return false;

    }

}
