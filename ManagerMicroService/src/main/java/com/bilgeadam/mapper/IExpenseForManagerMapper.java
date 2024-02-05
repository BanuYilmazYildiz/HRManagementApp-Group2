package com.bilgeadam.mapper;


import com.bilgeadam.rabbitmq.model.CreateExpenseModel;
import com.bilgeadam.repository.entity.ExpenseForManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IExpenseForManagerMapper {
    IExpenseForManagerMapper INSTANCE = Mappers.getMapper(IExpenseForManagerMapper.class);


    ExpenseForManager fromCreateExpenseModelToExpenseForManager(CreateExpenseModel model);
}
