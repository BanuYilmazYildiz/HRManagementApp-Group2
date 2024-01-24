package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreatePermissionRequestDto;
import com.bilgeadam.repository.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPermissionMapper {

   IPermissionMapper INSTANCE= Mappers.getMapper(IPermissionMapper.class);

    Permission fromCreatePermissionRequestDto(CreatePermissionRequestDto dto);

  // PermissionResponseDto fromPermissionToResponseDto(Permission permission);

}
