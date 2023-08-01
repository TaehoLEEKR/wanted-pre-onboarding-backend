package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.component.AuthTokens;
import com.example.wantedpreonboardingbackend.model.form.LoginForm;
import com.example.wantedpreonboardingbackend.model.form.UserForm;
import com.example.wantedpreonboardingbackend.model.repository.UserRepository;
import com.example.wantedpreonboardingbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody @Valid UserForm userForm){
        return ResponseEntity.ok(userService.userSignup(userForm));
    }
    @PostMapping("/sign-in")
    public ResponseEntity<AuthTokens> signInUser(@RequestBody @Valid LoginForm loginForm){
        AuthTokens tokens = userService.userSignIn(loginForm);
        return ResponseEntity.ok(tokens);
    }
}
