package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.component.jwt.CurrentUser;
import com.example.wantedpreonboardingbackend.component.jwt.LoginUser;
import com.example.wantedpreonboardingbackend.model.dto.CommunityListDto;
import com.example.wantedpreonboardingbackend.model.entity.Community;
import com.example.wantedpreonboardingbackend.model.form.CommunityCreateForm;
import com.example.wantedpreonboardingbackend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping("/create")
    public void createCommunity(
            @LoginUser CurrentUser loginUser,
            @RequestBody CommunityCreateForm communityCreateForm) {

        communityService.createCommunity(loginUser.getUserId(), communityCreateForm);
    }

    @GetMapping("/list")
    public CommunityListDto searchListCommunity(
            @RequestParam(value = "sort", defaultValue = "createDate") String sort,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size)
    {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
        return communityService.searchList(pageable);
    }
}
