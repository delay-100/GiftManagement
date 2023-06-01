package com.dbdbdip.giftmanagement.model.dto;

import com.dbdbdip.giftmanagement.model.entity.Role;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersForm {
    private String userId;
    private String password;
    private String nickname;
    private Role userRole;
}
