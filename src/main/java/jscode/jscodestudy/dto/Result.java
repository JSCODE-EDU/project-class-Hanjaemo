package jscode.jscodestudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {

    private T data;

    public static Result from(List list) {
        return new Result(list);
    }
}
