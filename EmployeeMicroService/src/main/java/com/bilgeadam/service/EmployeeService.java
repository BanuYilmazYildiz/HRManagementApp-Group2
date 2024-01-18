package com.bilgeadam.service;

import com.bilgeadam.dto.request.EmployeeUpdateRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.exception.EmployeeManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.mapper.IEmployeeMapper;
import com.bilgeadam.repository.EmployeeRepository;
import com.bilgeadam.repository.entity.Employee;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService extends ServiceManager<Employee,String> {

    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;

    public EmployeeService(EmployeeRepository employeeRepository,JwtTokenManager jwtTokenManager) {
        super(employeeRepository);
        this.employeeRepository=employeeRepository;
        this.jwtTokenManager=jwtTokenManager;
    }

    public Boolean updateUser(EmployeeUpdateRequestDto dto) {
        Optional<String> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()){
            throw  new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.BAD_REQUEST); //TODO
        }
        update(IEmployeeMapper.INSTANCE.fromUpdateDtoToEmployee(dto));
        UserUpdateRequestDto userUpdateRequestDto = IEmployeeMapper.INSTANCE.fromEmployeeToUserUpdateDto(employee.get());
        // user manager ile feign client yapılacak
        return true;
    }
}
