package com.example.wantedpreonboardingbackend.model.form;

import com.example.wantedpreonboardingbackend.model.entity.Community;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommunityCreateForm {
    private String communityName;
    private String content;

    public Community toEntity(){
        return Community.builder()
                .communityName(communityName)
                .content(content)
                .build();
    }
}
