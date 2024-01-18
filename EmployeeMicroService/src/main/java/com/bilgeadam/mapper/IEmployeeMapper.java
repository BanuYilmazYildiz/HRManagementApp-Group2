package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.EmployeeUpdateRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.repository.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeMapper {

    IEmployeeMapper INSTANCE = Mappers.getMapper(IEmployeeMapper.class);

    UserUpdateRequestDto fromEmployeeToUserUpdateDto(Employee employee);

    Employee fromUpdateDtoToEmployee (EmployeeUpdateRequestDto dto);
}
