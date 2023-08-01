package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.component.jwt.CurrentUser;
import com.example.wantedpreonboardingbackend.component.jwt.LoginUser;
import com.example.wantedpreonboardingbackend.model.entity.Community;
import com.example.wantedpreonboardingbackend.model.form.CommunityCreateForm;
import com.example.wantedpreonboardingbackend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/create")
    public void createCommunity(
            @LoginUser CurrentUser loginUser,
            @RequestBody CommunityCreateForm communityCreateForm){

        communityService.createCommunity(loginUser.getUserId(),communityCreateForm);
    }
}
