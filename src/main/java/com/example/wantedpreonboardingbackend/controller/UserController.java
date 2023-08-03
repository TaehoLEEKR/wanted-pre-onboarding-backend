package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.component.AuthTokens;
import com.example.wantedpreonboardingbackend.component.jwt.AuthTokensGenerator;
import com.example.wantedpreonboardingbackend.model.dto.UserDto;
import com.example.wantedpreonboardingbackend.model.form.LoginForm;
import com.example.wantedpreonboardingbackend.model.form.UserForm;
import com.example.wantedpreonboardingbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthTokensGenerator authTokensGenerator;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody @Valid UserForm userForm){
        return ResponseEntity.ok(userService.userSignup(userForm));
    }
    @PostMapping("/sign-in")
    public ResponseEntity<AuthTokens> signInUser(@RequestBody @Valid LoginForm loginForm){
        AuthTokens tokens = userService.userSignIn(loginForm);
        return ResponseEntity.ok(tokens);
    }
    @GetMapping("/{jwtToken}") // jwt토큰 사용자 정보 확인용
    public ResponseEntity<UserDto> findByJwt(@PathVariable String jwtToken){
        Long userId = authTokensGenerator.extractUserId(jwtToken);

        return ResponseEntity.ok(userService.getUserJwtInfo(userId));
    }
}
