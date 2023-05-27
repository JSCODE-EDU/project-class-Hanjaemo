package jscode.jscodestudy.controller;

import jscode.jscodestudy.dto.UserDto;
import jscode.jscodestudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    @ResponseStatus(CREATED)
    public String join(@Valid @RequestBody UserDto userDto) {
        userService.join(userDto);
        return "ok";
    }
}
