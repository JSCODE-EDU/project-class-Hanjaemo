package jscode.jscodestudy.exception;

import jscode.jscodestudy.exception.domain.PostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostException.class)
    ResponseEntity<ErrorResult> handlePostException(CustomException e) {
        log.error("CustomException : {}, {}", e.getHttpStatus(), e.getMessage());
        ErrorResult errorResult = new ErrorResult(e.getHttpStatus(), e.getMessage());
        return new ResponseEntity<>(errorResult, e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object processValidationError(MethodArgumentNotValidException e) {
        return new ErrorResult(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
