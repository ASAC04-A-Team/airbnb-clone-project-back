package com.example.airbnbbackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Entity
@NoArgsConstructor
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "host_id")
    private Long id;

    @Getter
    @Column(nullable = false)
    private Boolean grade;

    @Getter
    @Column(nullable = false)
    private LocalDateTime registerAt;

    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<Room> room;

    /* 연관관계 */
    public String getHostName() {
        return this.getUser().getNickname();
    }

    public String getHostProfileImageUrl() {
        return this.getUser().getProfileImageUrl();
    }

    public Integer getHostCareer(){
        LocalDateTime newRegisterAt = LocalDateTime.now();
        Period diff = Period.between(this.registerAt.toLocalDate(), newRegisterAt.toLocalDate());

        return diff.getYears();
    }
}
