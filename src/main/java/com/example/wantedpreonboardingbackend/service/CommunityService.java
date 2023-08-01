package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.exception.ErrorCode;
import com.example.wantedpreonboardingbackend.exception.GlobalException;
import com.example.wantedpreonboardingbackend.model.entity.Community;
import com.example.wantedpreonboardingbackend.model.entity.User;
import com.example.wantedpreonboardingbackend.model.form.CommunityCreateForm;
import com.example.wantedpreonboardingbackend.model.repository.CommunityRepository;
import com.example.wantedpreonboardingbackend.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    @Transactional
    public void createCommunity(
            Long userId, CommunityCreateForm communityCreateForm) {

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FIND_USER_ID));

        Community community = communityCreateForm.toEntity();

        community.setUser(user);

        communityRepository.save(community);
    }
}
