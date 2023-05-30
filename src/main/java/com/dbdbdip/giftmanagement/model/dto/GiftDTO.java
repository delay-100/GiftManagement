package com.dbdbdip.giftmanagement.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiftDTO {
    private String name;
    private int price;
    private String sales_link;
}
