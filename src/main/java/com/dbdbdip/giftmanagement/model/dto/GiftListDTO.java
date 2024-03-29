package com.dbdbdip.giftmanagement.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftListDTO {
    private Long giftId;
    private String name;
    private int price;
    private String category;
    private String sales_link;
    private String ceoNickName;
    private String ceoUserId;
}
