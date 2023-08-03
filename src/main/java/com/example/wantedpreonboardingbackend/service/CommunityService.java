package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.component.jwt.CurrentUser;
import com.example.wantedpreonboardingbackend.exception.ErrorCode;
import com.example.wantedpreonboardingbackend.exception.GlobalException;
import com.example.wantedpreonboardingbackend.model.dto.CommunityListDto;
import com.example.wantedpreonboardingbackend.model.dto.CommunityReadDto;
import com.example.wantedpreonboardingbackend.model.dto.CommunityUpdateForm;
import com.example.wantedpreonboardingbackend.model.entity.Community;
import com.example.wantedpreonboardingbackend.model.entity.User;
import com.example.wantedpreonboardingbackend.model.form.CommunityCreateForm;
import com.example.wantedpreonboardingbackend.model.repository.CommunityRepository;
import com.example.wantedpreonboardingbackend.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public CommunityListDto searchList(Pageable pageable) {
        Page<Community> communities = communityRepository.findAll(pageable);

        return CommunityListDto.from(communities);
    }

    @Transactional(readOnly=true)
    public CommunityReadDto getOne(Long communityId) {
        Community community = communityRepository.findByCommunityId(communityId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FIND_COMMUNITY_ID));

        CommunityReadDto communityReadDto = CommunityReadDto.from(community);

        return communityReadDto;
    }

    @Transactional
    public void updateCommunity(Long userId, Long communityId , CommunityUpdateForm communityUpdateForm) {
        Community community = communityRepository.findByCommunityIdAndUser_UserId(communityId,userId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FIND_COMMUNITY_ID));
        community.update(communityUpdateForm.getContent(), community.getCommunityName());
    }
    @Transactional
    public void deleteCommunity(Long userId, Long communityId )
    {
        Community community = communityRepository.findByCommunityIdAndUser_UserId(communityId,userId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FIND_COMMUNITY_ID));
        communityRepository.delete(community);
    }

}
