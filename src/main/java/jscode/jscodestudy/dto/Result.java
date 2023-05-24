package jscode.jscodestudy.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@ApiModel(value = "조회 응답")
public class Result<T> {

    private T data;

    public static Result from(List list) {
        return new Result(list);
    }
}
