package com.example.wantedpreonboardingbackend.model.form;

import com.example.wantedpreonboardingbackend.model.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @Email(message = "@ 이메일 형식에 맞지 않습니다")
    private String email;
    private String password;
    private String userName;
    private String phone;
    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .userName(userName)
                .phone(phone)
                .build();
    }
}
