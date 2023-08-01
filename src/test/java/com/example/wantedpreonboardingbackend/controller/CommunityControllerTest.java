package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.exception.ErrorCode;
import com.example.wantedpreonboardingbackend.exception.GlobalException;
import com.example.wantedpreonboardingbackend.model.entity.Community;
import com.example.wantedpreonboardingbackend.model.entity.User;
import com.example.wantedpreonboardingbackend.model.form.CommunityCreateForm;
import com.example.wantedpreonboardingbackend.model.repository.CommunityRepository;
import com.example.wantedpreonboardingbackend.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class CommunityControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CommunityRepository communityRepository;

    public static User user;
    public static CommunityCreateForm communityCreateForm;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .userName("test")
                .phone("0000")
                .email("community@community.com")
                .password("testtest")
                .build();
        userRepository.save(user);
        communityCreateForm = CommunityCreateForm
                .builder()
                .communityName("communityTest")
                .content("testtesttest")
                .build();
    }

    @Test
    @Transactional
    void createCommunity() {
       //given
        Long userId = userRepository.findByEmail("community@community.com").get().getUserId();

        User user1 = userRepository.findByUserId(userId)
               .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FIND_USER_ID));

       //when
        Community community = communityCreateForm.toEntity();
        community.setUser(user1);

        communityRepository.save(community);
        //then

        Long community_userId =communityRepository.findByCommunityIdAndUser_UserId(community.getCommunityId(),userId).get().getUser().getUserId();
        assertNotNull(community);
        System.out.println(userId);
        System.out.println(community_userId);
        assertEquals(userId,community_userId);
    }
}