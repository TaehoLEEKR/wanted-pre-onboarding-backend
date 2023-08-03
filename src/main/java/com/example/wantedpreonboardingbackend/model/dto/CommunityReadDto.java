package com.example.wantedpreonboardingbackend.model.dto;

import com.example.wantedpreonboardingbackend.model.entity.Community;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommunityReadDto {

    private String communityName;
    private String content;
    private String userName;
    private LocalDateTime createDate;


    public static CommunityReadDto from(Community community){
        return CommunityReadDto.builder()
                .communityName(community.getCommunityName())
                .content(community.getContent())
                .userName(community.getUser().getUserName())
                .createDate(community.getCreateDate())
                .build();
    }
}
