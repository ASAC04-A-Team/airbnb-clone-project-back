package com.example.airbnbbackend.dto.responseDto;

import com.example.airbnbbackend.domain.Host;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EachHostResponseDto {

    private String hostName;

    private String hostProfileImageUrl;

    private Boolean grade;

    private Integer hostCareer;


    public static EachHostResponseDto of(Host host){
        return new EachHostResponseDto(
                host.getHostName(),
                host.getHostProfileImageUrl(),
                host.getGrade(),
                host.getHostCareer()
        );
    }
}
