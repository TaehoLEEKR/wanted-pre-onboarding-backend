package com.example.wantedpreonboardingbackend.model.form;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotNull(message = "로그인 이메일을 작성해주세요.")
    @Email(message="@ 이메일 형식에 맞춰주세요.")
    private String loginEmail;
    private String password;
}
