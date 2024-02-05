package com.bilgeadam.mapper;

import com.bilgeadam.rabbitmq.model.CreateAdvanceModel;
import com.bilgeadam.repository.entity.AdvanceForManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdvanceForManagerMapper {
    IAdvanceForManagerMapper INSTANCE = Mappers.getMapper(IAdvanceForManagerMapper.class);

    AdvanceForManager fromCreateAdvanceModelToAdvanceForManager(CreateAdvanceModel model);
}
