package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.model.form.UserForm;
import com.example.wantedpreonboardingbackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody UserForm userForm){
        return ResponseEntity.ok(userService.UserSignup(userForm));
    }
}
