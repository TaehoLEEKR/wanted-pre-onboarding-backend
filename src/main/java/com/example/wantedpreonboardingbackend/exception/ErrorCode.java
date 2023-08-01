package com.example.wantedpreonboardingbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    WRONG_PASSWORD_INFO("패스워드 길이를 8자 이상 작성해 주세요")
    ;
    private final String MESSAGE;
}
