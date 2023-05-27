package jscode.jscodestudy.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "공백이 포함될 수 없습니다.")
    private String email;

    @NotBlank(message = "공백이 포함될 수 없습니다.")
    @Size(min = 8, max = 15, message = "패스워드는 8자 이상 15자 이하여야 합니다.")
    private String password;
}
