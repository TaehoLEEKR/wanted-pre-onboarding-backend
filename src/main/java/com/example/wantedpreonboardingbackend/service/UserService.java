package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.component.jwt.AuthTokensGenerator;
import com.example.wantedpreonboardingbackend.exception.ErrorCode;
import com.example.wantedpreonboardingbackend.exception.GlobalException;
import com.example.wantedpreonboardingbackend.component.AuthTokens;
import com.example.wantedpreonboardingbackend.model.dto.UserDto;
import com.example.wantedpreonboardingbackend.model.entity.User;
import com.example.wantedpreonboardingbackend.model.form.LoginForm;
import com.example.wantedpreonboardingbackend.model.form.UserForm;
import com.example.wantedpreonboardingbackend.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

/*
이메일과 비밀번호로 회원가입할 수 있는 엔드포인트를 구현해 주세요.
이메일과 비밀번호에 대한 유효성 검사를 구현해 주세요.
이메일 조건: @ 포함 : V
비밀번호 조건: 8자 이상 : V
비밀번호는 반드시 암호화하여 저장해 주세요. : V
이메일과 비밀번호의 유효성 검사는 위의 조건만으로 진행해 주세요. 추가적인 유효성 검사 조건은 포함하지 마세요.
*/
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokensGenerator authTokensGenerator;
    @Transactional
    public String userSignup(UserForm userForm) {

        if(isEmailExist(userForm.getEmail())){
            throw new GlobalException(ErrorCode.ALREADY_USER_EMAIL);
        }

        User user = userForm.toEntity();
        String userPassword = ValidPassword(user.getPassword());

        user.setPassword(userPassword);
        userRepository.save(user);

        return "회원 가입 성공 하였습니다.";
    }
    public boolean isEmailExist(String email){
        return userRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }
    public String ValidPassword(String password){
        if(password.length() < 8){
            throw new GlobalException(ErrorCode.WRONG_PASSWORD_INFO);
        }
        String validPassword =passwordEncoder.encode(password);

        return validPassword;
    }

    @Transactional
    public AuthTokens userSignIn(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getLoginEmail())
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FIND_USER_EMAIL));

        if(loginForm.getPassword().length() < 8){
            throw new GlobalException(ErrorCode.WRONG_PASSWORD_INFO);
        }
        if(!passwordEncoder.matches(loginForm.getPassword(),user.getPassword())){
            throw new GlobalException(ErrorCode.WRONG_PASSWORD_LOGIN);
        }
    return authTokensGenerator.generate(user.getUserId());
    }

    public UserDto getUserJwtInfo(Long userId) {
        User user = userRepository.findByUserId(userId).get();
        return UserDto.from(user);
    }
}
