package jscode.jscodestudy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResult {

    private HttpStatus httpStatus;
    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }
}
