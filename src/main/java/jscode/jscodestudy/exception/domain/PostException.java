package jscode.jscodestudy.exception.domain;

import jscode.jscodestudy.exception.CustomException;
import jscode.jscodestudy.exception.ErrorCode;

public class PostException extends CustomException {

    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
