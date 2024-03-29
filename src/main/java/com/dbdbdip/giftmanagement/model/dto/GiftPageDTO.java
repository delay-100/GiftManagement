package com.dbdbdip.giftmanagement.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftPageDTO {
    private Long giftId;
    private String name;
    private int price;
    private String category;
    private String sales_link;
    private boolean likes;
    private String ceoNickName;
    private String ceoUserId;
}
