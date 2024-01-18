package com.bilgeadam.service;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.repository.UserRepository;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,String> {

    private final UserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    public UserService(UserRepository userRepository,JwtTokenManager jwtTokenManager) {
        super(userRepository);
        this.userRepository=userRepository;
        this.jwtTokenManager=jwtTokenManager;
    }

    public String login(LoginRequestDto dto) {
        Optional<User> userOptional = userRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if (userOptional.isEmpty()){
            throw new UserManagerException(ErrorType.LOGIN_ERROR);
        }
        Optional<String> token = jwtTokenManager.createToken(userOptional.get().getId(),userOptional.get().getRole());
        if(token.isEmpty()){
            throw new UserManagerException(ErrorType.TOKEN_NOT_CREATED);
        }
        return token.get();

    }
}
