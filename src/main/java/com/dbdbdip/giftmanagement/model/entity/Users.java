package com.dbdbdip.giftmanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Column(name="user_id")
    private String userId;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role userRole;    //User , CEO, Admin

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}

