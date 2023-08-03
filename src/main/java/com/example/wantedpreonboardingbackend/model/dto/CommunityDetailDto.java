package com.example.wantedpreonboardingbackend.model.dto;

import com.example.wantedpreonboardingbackend.model.entity.Community;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityDetailDto {

    private Long communityId;
    private Long userId;
    private String communityName;
    private LocalDateTime createdDate;

    public static CommunityDetailDto from(Community community){
        return CommunityDetailDto.builder()
                .communityId(community.getCommunityId())
                .communityName(community.getCommunityName())
                .userId(community.getUser().getUserId())
                .createdDate(community.getCreateDate())
                .build();
    }
}
