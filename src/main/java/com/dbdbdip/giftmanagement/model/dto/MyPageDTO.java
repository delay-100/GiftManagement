package com.dbdbdip.giftmanagement.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageDTO {
    private String nickname;
    private String currentPassword;
    private String newPassword;
}
