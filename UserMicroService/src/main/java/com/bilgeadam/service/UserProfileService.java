package com.bilgeadam.service;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.manager.IEmployeeManager;
import com.bilgeadam.manager.IManagerManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.UserProfileRepository;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import com.bilgeadam.utility.enums.ERole;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final UserProfileRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IEmployeeManager employeeManager;
    private final IManagerManager managerManager;
    public UserProfileService(UserProfileRepository userRepository, JwtTokenManager jwtTokenManager,IEmployeeManager employeeManager,IManagerManager managerManager) {
        super(userRepository);
        this.userRepository=userRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.employeeManager=employeeManager;
        this.managerManager =managerManager;
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        Optional<UserProfile> userOptional = userRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if (userOptional.isEmpty()){
            throw new UserManagerException(ErrorType.LOGIN_ERROR);
        }
        Optional<String> token = jwtTokenManager.createToken(userOptional.get().getId(),userOptional.get().getRole());
        if(token.isEmpty()){
            throw new UserManagerException(ErrorType.TOKEN_NOT_CREATED);
        }
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .token(token.get())
                .userId(userOptional.get().getId())
                .role(userOptional.get().getRole())
                .build();
    return loginResponseDto;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        UserProfile userProfile = IUserMapper.INSTANCE.fromUserRegisterRequestDtoToUser(dto);
        save(userProfile);
        if (userProfile.getRole().equals(ERole.EMPLOYEE)){
            employeeManager.createEmployee(IUserMapper.INSTANCE.fromUserToEmployeeCreateRequestDto(userProfile));
        }else {
            managerManager.createManager(IUserMapper.INSTANCE.fromUserToCreateManagerRequestDto(userProfile));
        }
        return IUserMapper.INSTANCE.fromUserToRegisterResponseDto(userProfile);
    }
}
