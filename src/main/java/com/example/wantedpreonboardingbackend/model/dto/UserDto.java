package com.example.wantedpreonboardingbackend.model.dto;

import com.example.wantedpreonboardingbackend.model.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String email;
    private String password;
    private String userName;
    private String phone;

    public static UserDto from(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .userName(user.getUserName())
                .phone(user.getPhone())
                .build();
    }
}
