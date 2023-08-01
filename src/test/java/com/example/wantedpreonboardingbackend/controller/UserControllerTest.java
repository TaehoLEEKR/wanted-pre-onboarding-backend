package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.model.entity.User;
import com.example.wantedpreonboardingbackend.model.form.UserForm;
import com.example.wantedpreonboardingbackend.model.repository.UserRepository;
import com.example.wantedpreonboardingbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @Test
    public UserForm baseData(String email, String password){
        //given
        UserForm userForm = UserForm.builder()
                .userName("test")
                .phone("1231234")
                .email(email)
                .password(password)
                .build();
        //when
        //then
        return userForm;
    }
    @Transactional
    @Test
    void 회원가입_성공() throws Exception{
        //given
        UserForm userForm = baseData("test@test.com","password1");
        User user = userForm.toEntity();

        //when
        String userPassword = userService.ValidPassword(user.getPassword());
        user.setPassword(userPassword);
        userRepository.save(user);
        //then
        assertNotNull(user);
    }
    @Transactional
    @Test
    void 회원가입_이메일실패() throws Exception{
        //given
        UserForm userForm = baseData("testtest.com","password1");
        User user = userForm.toEntity();

        //when
        String userPassword = userService.ValidPassword(user.getPassword());
        user.setPassword(userPassword);
        userRepository.save(user);
        boolean containsAts = user.getEmail().contains("@");
        //then
        assertTrue(containsAts);
    }
    @Transactional
    @Test
    void 회원가입_패스워드실패() throws Exception{
        //given
        UserForm userForm = baseData("test@test.com","pass");
        User user = userForm.toEntity();

        //when
        String userPassword = userService.ValidPassword(user.getPassword());
        user.setPassword(userPassword);
        userRepository.save(user);
        //then
    }
}