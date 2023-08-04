package com.example.wantedpreonboardingbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ALREADY_USER_EMAIL("이미 존재하는 이메일입니다."),
    NOT_FIND_USER_ID("존재 하지 않는 유저 입니다."),
    WRONG_PASSWORD_INFO("패스워드 길이를 8자 이상 작성해 주세요"),
    NOT_FIND_USER_EMAIL("이메일이 존재하지 않습니다."),
    WRONG_PASSWORD_LOGIN("잘못 된 패스워드 입니다."),
    NOT_FIND_COMMUNITY_UPDATE("게시판을 수정 할 수 없습니다."),
    NOT_FIND_COMMUNITY_ID("게시판이 존재하지 않습니다."),
    NOT_FIND_COMMUNITY_ID_DELETE("게시판을 삭제 할 수 없습니다.")
    ;
    private final String MESSAGE;
}
