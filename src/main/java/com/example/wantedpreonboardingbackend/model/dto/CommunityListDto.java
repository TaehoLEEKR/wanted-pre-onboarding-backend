package com.example.wantedpreonboardingbackend.model.dto;

import com.example.wantedpreonboardingbackend.model.entity.Community;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CommunityListDto {

    private List<CommunityDetailDto> communities;
    private int totalPages;


    public static CommunityListDto from(Page<Community> communities) {

        List<CommunityDetailDto> postsElementResponses =
                communities.getContent()
                        .stream()
                        .map(CommunityDetailDto::from)
                        .collect(Collectors.toList());

        return new CommunityListDto(postsElementResponses, communities.getTotalPages());
    }
}
