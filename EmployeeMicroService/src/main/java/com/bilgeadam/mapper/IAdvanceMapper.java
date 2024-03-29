package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateAdvanceRequestDto;
import com.bilgeadam.rabbitmq.model.CreateAdvanceModel;
import com.bilgeadam.repository.entity.Advance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdvanceMapper {

    IAdvanceMapper INSTANCE = Mappers.getMapper(IAdvanceMapper.class);

    Advance fromCreateAdvanceRequestDtoToAdvance(CreateAdvanceRequestDto dto);
    @Mapping(source ="id" ,target = "advanceId")
    CreateAdvanceModel fromAdvanceToAdvanceModel(Advance advance);
}
