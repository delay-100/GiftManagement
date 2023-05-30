package com.dbdbdip.giftmanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role userRole;    //User , CEO, Admin



}
