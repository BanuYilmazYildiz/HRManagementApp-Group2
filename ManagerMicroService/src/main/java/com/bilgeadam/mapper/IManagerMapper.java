package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateManagerRequestDto;
import com.bilgeadam.dto.reponse.ManagerFindByUserIdDetailResponseDto;
import com.bilgeadam.repository.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IManagerMapper {
    IManagerMapper INSTANCE = Mappers.getMapper(IManagerMapper.class);

    Manager fromCreateRequestToManager(CreateManagerRequestDto dto);

    ManagerFindByUserIdDetailResponseDto fromManagerToFindByIdDetailDtoTo(Manager manager);
}
