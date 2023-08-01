package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.model.entity.User;
import com.example.wantedpreonboardingbackend.model.form.UserForm;
import com.example.wantedpreonboardingbackend.model.repository.UserRepository;
import com.example.wantedpreonboardingbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody @Valid UserForm userForm){
        return ResponseEntity.ok(userService.UserSignup(userForm));
    }
    @GetMapping("/read")
    public ResponseEntity<Optional<User>> findUser(@RequestParam String email){
        return ResponseEntity.ok(userRepository.findByEmail(email));
    }
}
