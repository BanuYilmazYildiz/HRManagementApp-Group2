package com.bilgeadam.service;

import com.bilgeadam.dto.request.ChangePasswordRequestDto;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final UserProfileRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IEmployeeManager employeeManager;
    private final IManagerManager managerManager;
    private final RegisterMailProducer registerMailProducer;
   private final PasswordResetProducer passwordResetProducer;
    private static Map<String, Integer> nameCountMap = new HashMap<>();

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
                .activationCode(userOptional.get().getActivationCode())
                .password(userOptional.get().getPassword())
                .build();
    return loginResponseDto;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        UserProfile userProfile = IUserMapper.INSTANCE.fromUserRegisterRequestDtoToUser(dto);
//        String email = (dto.getName().replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
//                .replaceAll("ı", "i")
//                .replaceAll("İ", "I")
//                .toLowerCase()+"."
//                +dto.getSurname().replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
//                .replaceAll("ı", "i")
//                .replaceAll("İ", "I")
//                .toLowerCase()
//                +"@"+
//                dto.getCompany().replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
//                        .replaceAll("ı", "i")
//                        .replaceAll("İ", "I")
//                        .toLowerCase()
//                +".com");
        String email = (StringUtils.stripAccents(dto.getName()).toLowerCase()+"."
                +StringUtils.stripAccents(dto.getSurname()).toLowerCase()
                +"@"+
                StringUtils.stripAccents(dto.getCompany()).toLowerCase()
                +".com");

        userProfile.setEmail(email);
        userProfile.setActivationCode(CodeGenerator.generateCode());
        userProfile.setPassword(userProfile.getActivationCode());
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
            String newActivationCode = CodeGenerator.generateCode();
            userProfile.get().setActivationCode(newActivationCode);
            userProfile.get().setPassword(newActivationCode);
            update(userProfile.get());
            passwordResetProducer.sendActivationCode(IUserMapper.INSTANCE.fromUserToPasswordResetMailModel(userProfile.get()));
        }
        return IUserMapper.INSTANCE.fromUserToPasswordResetResponseDto(userProfile.get());
    }

    public Boolean changePassword(ChangePasswordRequestDto dto) {
      Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
      if (userId.isEmpty()){
          throw new UserManagerException(ErrorType.INVALID_TOKEN);
      }
      Optional<UserProfile> userProfile = userRepository.findOptionalById(userId.get());
      if (userProfile.isEmpty()){
          throw new UserManagerException(ErrorType.USER_NOT_FOUND);
      }
      if (!dto.getPassword().equals(dto.getPasswordConfirmation())){
          throw new UserManagerException(ErrorType.PASSWORD_MISSMATCH);
      }
        userProfile.get().setPassword(dto.getPassword());
        update(userProfile.get());
        return true;
    }


//    public RegisterResponseDto registerWithRabbitMQ(RegisterRequestDto dto) {
//        UserProfile userProfile = IUserMapper.INSTANCE.fromUserRegisterRequestDtoToUser(dto);
//        userProfile.setEmail(dto.getName()+"." +dto.getSurname()+"@"+dto.getCompany()+".com");
//        userProfile.setActivationCode(CodeGenerator.generateCode());
//    }
}
