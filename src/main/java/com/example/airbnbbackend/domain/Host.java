package com.example.airbnbbackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "host_id")
    private Long id;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private LocalDateTime registerAt;

    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /* 연관관계 */
    public String getHostName() {
        return this.getUser().getNickname();
    }
}
