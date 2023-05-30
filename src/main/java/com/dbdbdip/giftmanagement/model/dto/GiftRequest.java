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
    private int price;
    private String salesLink;

}
