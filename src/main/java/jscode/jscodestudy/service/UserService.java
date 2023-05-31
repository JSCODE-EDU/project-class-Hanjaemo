package jscode.jscodestudy.service;

import jscode.jscodestudy.config.jwt.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

    public void join(UserDto userDto) {
        if (!checkEmail(userDto.getEmail())) {
            throw new CustomException(ErrorCode.BAD_REQUEST_EXISTS_EMAIL);
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        userRepository.join(user);
    }

    public String login(String email, String password) {

        User user = userRepository.findUser(email, password);
        
        String token = jwtTokenProvider.createToken(user.getId(), email, (2 * 1000 * 60));

        return token;
    }


    private boolean checkEmail(String email) {
        return userRepository.checkEmail(email) == 0;
    }
}