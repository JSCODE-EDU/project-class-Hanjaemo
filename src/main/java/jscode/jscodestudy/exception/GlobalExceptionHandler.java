package jscode.jscodestudy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static jscode.jscodestudy.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    ResponseEntity<ErrorResult> handlePostException(CustomException e) {
        log.error("CustomException : {}, {}", e.getHttpStatus(), e.getMessage());
        ErrorResult errorResult = new ErrorResult(e.getHttpStatus(), e.getMessage());
        return new ResponseEntity<>(errorResult, e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object processValidationError(MethodArgumentNotValidException e) {
        return new ErrorResult(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResult> handleGlobalException(Throwable e) {
        log.error("message : {}", e.getMessage());
        ErrorResult errorResult = new ErrorResult(INTERNAL_SERVER_ERROR.getHttpStatus(), INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity<>(errorResult, errorResult.getHttpStatus());
    }
}
