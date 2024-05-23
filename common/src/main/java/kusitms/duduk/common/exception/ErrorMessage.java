package kusitms.duduk.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    USER_NOT_FOUND("해당 유저를 찾을 수 없습니다."),
    NEWS_LETTER_NOT_FOUND("해당 뉴스레터를 찾을 수 없습니다."),
    EDITOR_NOT_FOUND("해당 에디터를 찾을 수 없습니다."),
    TERM_NOT_FOUND("해당 단어를 찾을 수 없습니다."),
    EXIST_EMAIL("이미 존재 하는 이메일 입니다."),
    EXIST_NICKNAME("이미 존재 하는 닉네임 입니다."),
    UNAUTHORIZE_USER("해당 유저는 권한이 없습니다."),
    UNAUTHORIZE_EDITOR_ROLE("해당 유저는 에디터 권한이 없습니다."),
    ;

    private final String message;
}
