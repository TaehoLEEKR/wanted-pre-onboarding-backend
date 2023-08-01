package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.component.AuthTokens;
import com.example.wantedpreonboardingbackend.model.entity.User;
import com.example.wantedpreonboardingbackend.model.form.LoginForm;
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
    @Test
    @Transactional
    void 로그인_성공() throws Exception {
        //given
        UserForm userForm = baseData("test@test.com","password1");
        User user = userForm.toEntity();

        //when
        String userPassword = userService.ValidPassword(user.getPassword());
        user.setPassword(userPassword);
        userRepository.save(user);
        //when
        LoginForm loginForm = LoginForm.builder()
                .loginEmail(user.getEmail())
                .password("password1")
                .build();
       AuthTokens result =  userService.userSignIn(loginForm);

        //then
        assertNotNull(result);
    }
    @Test
    @Transactional
    void 로그인_이메일_실패() throws Exception {
        //given
        UserForm userForm = baseData("test@test.com","password1");
        User user = userForm.toEntity();

        //when
        String userPassword = userService.ValidPassword(user.getPassword());
        user.setPassword(userPassword);
        userRepository.save(user);
        //when
        LoginForm loginForm = LoginForm.builder()
                .loginEmail("testtest.com")
                .password("password1")
                .build();
        AuthTokens result =  userService.userSignIn(loginForm);
        //then
        boolean containsAts = loginForm.getLoginEmail().contains("@");
        assertTrue(containsAts);
        // 이메일 형식 || 이메일이 존재하지 않습니다.
        assertNotNull(result);
    }
    @Test
    @Transactional
    void 로그인_PW_불일치() throws Exception {
        //given
        UserForm userForm = baseData("test@test.com","123456789");
        User user = userForm.toEntity();

        //when
        String userPassword = userService.ValidPassword(user.getPassword());
        user.setPassword(userPassword);
        userRepository.save(user);
        //when
        LoginForm loginForm = LoginForm.builder()
                .loginEmail(user.getEmail())
                .password("password1")
                .build();

        //then
        assertTrue(passwordEncoder.matches(loginForm.getPassword(),user.getPassword()));
    }
    @Test
    @Transactional
    void 로그인_PW_길이실패() throws Exception {
        //given
        UserForm userForm = baseData("test@test.com","123456789");
        User user = userForm.toEntity();

        //when
        String userPassword = userService.ValidPassword(user.getPassword());
        user.setPassword(userPassword);
        userRepository.save(user);
        //when
        LoginForm loginForm = LoginForm.builder()
                .loginEmail(user.getEmail())
                .password("1234")
                .build();
        AuthTokens result =  userService.userSignIn(loginForm);
        //then
        assertNotNull(result);
    }
}