package com.example.airbnbbackend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SuperBuilder
@Entity
@NoArgsConstructor
@Getter
@ToString
public class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String pw;

    @Column
    @Getter
    private String profileImageUrl;

    @Column(nullable = false)
    @Getter
    private String nickname;

    @Column(nullable = false)
    @Getter
    private String nation;

    @Column(nullable = false)
    private String address;

    private String emailAuthCode;

    @Column(nullable = false)
    private LocalDateTime registerAt;

    private LocalDateTime birthDay;

    @Column(nullable = false)
    private Boolean isHost;


    @Column(nullable = false)
    private Boolean isAuth;

    @Column(nullable = false)
    private String userToken;

    // 관리자, 유저 추가
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Host host;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HostReview> HostReviews = new ArrayList<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInterest userInterests;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        // role 은 관리자냐 사용자냐에 따라 권한을 주는 것.
        // 따라서 팀원들과 합의 후 진행. -> Role을 위한 컬럼 하나 더 생성 필요.
    }

    @Override
    public String getPassword() {
        return this.pw;
    }

    // 각각에 대해서 주석달기
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() { // email로 담을 것
        return this.email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(Long id, String email, String pw, String nickname, String nation, String address, LocalDateTime registerAt, Boolean isHost, Boolean isAuth, String userToken) {
        this.id = id;
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.nation = nation;
        this.address = address;
        this.registerAt = registerAt;
        this.isHost = isHost;
        this.isAuth = isAuth;
        this.userToken = userToken;
    }


//    public User(SignUpRequestDto dto) {
//        this.id = dto.getId();
//        this.email = dto.getEmail();
//        this.pw = dto.getPw();
//        // 잠깐 여기까지
//    }

//    public static User createUser(String nickname, LocalDateTime birthDay, String email, String password, String emailAuthCode){
//        User user = new User();
//        user.nickname = nickname;
//        user.birthDay = birthDay;
//        user.email = email;
//        user.pw = password;
//        user.emailAuthCode = emailAuthCode;
//        return user;
//    }
}