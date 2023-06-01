package com.dbdbdip.giftmanagement.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftRequest {
    private String userId;
    private String giftId;

    private String name;
    private String category;
    private int price;
    private String salesLink;

}
