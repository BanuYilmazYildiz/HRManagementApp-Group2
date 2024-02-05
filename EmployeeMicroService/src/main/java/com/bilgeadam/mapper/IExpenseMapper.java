package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.rabbitmq.model.CreateExpenseModel;
import com.bilgeadam.repository.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IExpenseMapper {
    IExpenseMapper INSTANCE = Mappers.getMapper(IExpenseMapper.class);

    Expense fromCreateManagerExceptionDtoToExpense(CreateExpenseRequestDto dto);

    @Mapping(source ="id" ,target = "expenseId")
    CreateExpenseModel fromEmployeeToExpenseModel(Expense expense);
}
