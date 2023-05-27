package jscode.jscodestudy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    BAD_REQUEST_SEARCH_TITLE(HttpStatus.BAD_REQUEST, "검색 키워드는 공백을 제외한 1글자 이상이어야 합니다."),
    BAD_REQUEST_EXISTS_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    BAD_REQUEST_EXISTS_PASSWORD(HttpStatus.BAD_REQUEST, "중복된 패스워드입니다."),

    // 404
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
