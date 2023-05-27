package jscode.jscodestudy.service;

import jscode.jscodestudy.domain.User;
import jscode.jscodestudy.dto.UserDto;
import jscode.jscodestudy.exception.CustomException;
import jscode.jscodestudy.exception.ErrorCode;
import jscode.jscodestudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void join(UserDto userDto) {
        if (!checkEmail(userDto.getEmail())) {
            throw new CustomException(ErrorCode.BAD_REQUEST_EXISTS_EMAIL);
        }

        if (!checkPassword(userDto.getPassword())) {
            throw new CustomException(ErrorCode.BAD_REQUEST_EXISTS_PASSWORD);
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        userRepository.join(user);
    }

    public boolean checkEmail(String email) {
        return userRepository.checkEmail(email) == 0;
    }

    public boolean checkPassword(String password) {
        return userRepository.checkPassword(password) == 0;
    }
}
