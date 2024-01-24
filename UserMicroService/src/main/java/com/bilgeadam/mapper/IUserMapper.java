package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateManagerRequestDto;
import com.bilgeadam.dto.request.EmployeeCreateRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserProfile fromUserRegisterRequestDtoToUser(final RegisterRequestDto dto);

    @Mapping(source ="id" ,target = "userId")
    EmployeeCreateRequestDto fromUserToEmployeeCreateRequestDto(UserProfile userProfile);
    @Mapping(source = "id",target = "userId")
    CreateManagerRequestDto fromUserToCreateManagerRequestDto(UserProfile userProfile);

    RegisterResponseDto fromUserToRegisterResponseDto(UserProfile userProfile);
}
