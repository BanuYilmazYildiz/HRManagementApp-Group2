package com.bilgeadam.mapper;

import com.bilgeadam.rabbitmq.model.CreatePermissionModel;
import com.bilgeadam.repository.entity.PermissionForManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPermissionForManagerMapper {

    IPermissionForManagerMapper INSTANCE = Mappers.getMapper(IPermissionForManagerMapper.class);

    PermissionForManager fromCreatePermissionModelToExpenseForManager(CreatePermissionModel model);

}