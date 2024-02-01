package com.bilgeadam.service;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.PasswordResetRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.PasswordResetResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.manager.IEmployeeManager;
import com.bilgeadam.manager.IManagerManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.rabbitmq.producer.PasswordResetProducer;
import com.bilgeadam.rabbitmq.producer.RegisterMailProducer;
import com.bilgeadam.repository.UserProfileRepository;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.utility.CodeGenerator;
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
    private final RegisterMailProducer registerMailProducer;
   private final PasswordResetProducer passwordResetProducer;
    public UserProfileService(UserProfileRepository userRepository, JwtTokenManager jwtTokenManager, IEmployeeManager employeeManager, IManagerManager managerManager, RegisterMailProducer registerMailProducer, PasswordResetProducer passwordResetProducer) {
        super(userRepository);
        this.userRepository=userRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.employeeManager=employeeManager;
        this.managerManager =managerManager;
        this.registerMailProducer = registerMailProducer;
        this.passwordResetProducer = passwordResetProducer;
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
                .company(userOptional.get().getCompany())
                .build();
    return loginResponseDto;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        UserProfile userProfile = IUserMapper.INSTANCE.fromUserRegisterRequestDtoToUser(dto);
        userProfile.setEmail(dto.getName()+"." +dto.getSurname()+"@"+dto.getCompany()+".com");
        userProfile.setActivationCode(CodeGenerator.generateCode());
        save(userProfile);
        if (userProfile.getRole().equals(ERole.EMPLOYEE)){
            employeeManager.createEmployee(IUserMapper.INSTANCE.fromUserToEmployeeCreateRequestDto(userProfile));
            registerMailProducer.sendActivationCode(IUserMapper.INSTANCE.fromUserToRegisterMailModel(userProfile));
        }else {
            managerManager.createManager(IUserMapper.INSTANCE.fromUserToCreateManagerRequestDto(userProfile));
            registerMailProducer.sendActivationCode(IUserMapper.INSTANCE.fromUserToRegisterMailModel(userProfile));
        }
        return IUserMapper.INSTANCE.fromUserToRegisterResponseDto(userProfile);
    }

    public PasswordResetResponseDto passwordReset(PasswordResetRequestDto dto) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByPersonalEmail(dto.getPersonalEmail());
        if (userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        } else {
            userProfile.get().setActivationCode(CodeGenerator.generateCode());
            passwordResetProducer.sendActivationCode(IUserMapper.INSTANCE.fromUserToPasswordResetMailModel(userProfile.get()));
        }
        return IUserMapper.INSTANCE.fromUserToPasswordResetResponseDto(userProfile.get());
    }




//    public RegisterResponseDto registerWithRabbitMQ(RegisterRequestDto dto) {
//        UserProfile userProfile = IUserMapper.INSTANCE.fromUserRegisterRequestDtoToUser(dto);
//        userProfile.setEmail(dto.getName()+"." +dto.getSurname()+"@"+dto.getCompany()+".com");
//        userProfile.setActivationCode(CodeGenerator.generateCode());
//    }
}
