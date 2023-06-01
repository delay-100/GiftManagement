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
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gift_id")
    private Long giftId;
    private String name;
    private String category;
    private int price;
    private String salesLink;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userId;

}
