package com.bilgeadam.service;

import com.bilgeadam.config.CloudinaryConfig;
import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.AdvanceResponseDto;
import com.bilgeadam.dto.response.EmployeeFindByUserIdDetailResponseDto;
import com.bilgeadam.dto.response.ExpenseResponseDto;
import com.bilgeadam.dto.response.PermissionResponseDto;
import com.bilgeadam.exception.EmployeeManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.mapper.IAdvanceMapper;
import com.bilgeadam.mapper.IEmployeeMapper;
import com.bilgeadam.mapper.IExpenseMapper;
import com.bilgeadam.mapper.IPermissionMapper;
import com.bilgeadam.repository.EmployeeRepository;
import com.bilgeadam.repository.ExpenseRepository;
import com.bilgeadam.repository.IAdvanceRepository;
import com.bilgeadam.repository.PermissionRepository;
import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.repository.entity.Employee;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.entity.Permission;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends ServiceManager<Employee,String> {

    private final EmployeeRepository employeeRepository;
    private final PermissionRepository permissionRepository;
    private final IAdvanceRepository advanceRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CloudinaryConfig cloudinaryConfig;

    private final ExpenseRepository expenseRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PermissionRepository permissionRepository, IAdvanceRepository advanceRepository, JwtTokenManager jwtTokenManager, CloudinaryConfig cloudinaryConfig, ExpenseRepository expenseRepository) {
        super(employeeRepository);
        this.employeeRepository=employeeRepository;
        this.permissionRepository = permissionRepository;
        this.advanceRepository = advanceRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.cloudinaryConfig = cloudinaryConfig;
        this.expenseRepository = expenseRepository;
    }

    public Boolean updateUser(EmployeeUpdateRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()){
            throw  new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.BAD_REQUEST);
        }
        employee.get().setPhone(dto.getPhone());
        employee.get().setAddress(dto.getAddress());
        update(employee.get());

        //UserUpdateRequestDto userUpdateRequestDto = IEmployeeMapper.INSTANCE.fromEmployeeToUserUpdateDto(employee.get());
        return true;
    }




    public Boolean createUser(EmployeeCreateRequestDto dto) {
        try {
            save(IEmployeeMapper.INSTANCE.fromCreateRequestToEmployee(dto));
            return true;
        } catch (Exception e){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_CREATED);
        }
    }

    public EmployeeFindByUserIdDetailResponseDto findOptionalByIdDetail(Long userId) {
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId);
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        EmployeeFindByUserIdDetailResponseDto responseDto = IEmployeeMapper.INSTANCE.fromEmployeeToFindByIdDetailDtoTo(employee.get());
        return responseDto;
    }

    public EmployeeFindByUserIdDetailResponseDto findEmployee2(Long userId) {
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId);
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        //EmployeeFindByUserIdResponseDto responseDto = IEmployeeMapper.INSTANCE.fromEmployeeToFindByIdDtoTo(employee.get());
        EmployeeFindByUserIdDetailResponseDto responseDto = IEmployeeMapper.INSTANCE.fromEmployeeToFindByIdDetailDtoTo(employee.get());
        return responseDto;
    }



    public String imageUpload(MultipartFile file){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudinaryConfig.getCloud_name());
        config.put("api_key", cloudinaryConfig.getApi_key());
        config.put("api_secret", cloudinaryConfig.getApi_secret());

        Cloudinary cloudinary = new Cloudinary(config);

        try {
            Map<?,?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean updateImage(ImageUploadRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if(userId.isEmpty()){
            throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        String url = imageUpload(dto.getPhoto());
        employee.get().setPhoto(url);
        update(employee.get());
        return true;
    }

    public Boolean createPermission(CreatePermissionRequestDto dto){
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()) throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_CREATED);
        }
        Permission permission = IPermissionMapper.INSTANCE.fromCreatePermissionRequestDto(dto);
        permission.setUserId(userId.get());
        permissionRepository.save(permission);
        return true;
    }



    public Boolean createExpense(CreateExpenseRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()){
            throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        Expense expense = IExpenseMapper.INSTANCE.fromCreateManagerExceptionDtoToExpense(dto);
        expense.setUserId(userId.get());
        expense.setName(employee.get().getName());
        expense.setSurname(employee.get().getSurname());
        expenseRepository.save(expense);
        return true;
    }




    public Boolean updateStatusPermission(UpdateStatusRequestDto dto) {
        Optional<Permission> permission = permissionRepository.findById(dto.getId());
        if (permission.isEmpty()){
            throw new EmployeeManagerException(ErrorType.REQUEST_NOT_FOUND);
        }
        permission.get().setApprovalStatus(dto.getApprovalStatus());
        permission.get().setReplyDate(LocalDate.now());
        permissionRepository.save(permission.get());
        return true;
    }

    public Boolean createAdvance(CreateAdvanceRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()) throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_CREATED);
        }
        Advance advance = IAdvanceMapper.INSTANCE.fromCreateAdvanceRequestDtoToAdvance(dto);
        advance.setUserId(userId.get());
        advanceRepository.save(advance);
        return  true;
    }

    public Boolean updateStatusAdvance(UpdateStatusRequestDto dto) {
        Optional<Advance> advance = advanceRepository.findById(dto.getId());
        if (advance.isEmpty()){
            throw new EmployeeManagerException(ErrorType.REQUEST_NOT_FOUND);
        }
        advance.get().setApprovalStatus(dto.getApprovalStatus());
        advance.get().setReplyDate(LocalDate.now());
        advanceRepository.save(advance.get());
        return true;
    }


    public List<ExpenseResponseDto> findAllExpensestoken(String token) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(token);
        if (userId.isEmpty()) {
            throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> user = employeeRepository.findOptionalByUserId(userId.get());
        if (user.isEmpty()) {
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        return expenseRepository.findAllByUserId(userId.get()).stream().map(a->{
            return ExpenseResponseDto.builder()
                    .expenseAmount(a.getExpenseAmount())
                    .expenseType(a.getExpenseType())
                    .dateOfResponse(a.getDateOfResponse())
                    .approvalStatus(a.getApprovalStatus())
                    .currency(a.getCurrency()).build();
        }).collect(Collectors.toList());

    }

    public List<AdvanceResponseDto> findAllAdvance(String token) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(token);
        if (userId.isEmpty()) {
            throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> user = employeeRepository.findOptionalByUserId(userId.get());
        if (user.isEmpty()) {
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        return advanceRepository.findAllByUserId(userId.get()).stream().map(a->{
            return AdvanceResponseDto.builder()
                    .amountOfRequest(a.getAmountOfRequest())
                    .approvalStatus(a.getApprovalStatus())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<PermissionResponseDto> findAllPermission(String token) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(token);
        if (userId.isEmpty()) {
            throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> user = employeeRepository.findOptionalByUserId(userId.get());
        if (user.isEmpty()) {
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        return permissionRepository.findAllByUserId(userId.get()).stream().map(a->{
            return PermissionResponseDto.builder()
                    .permissionType(a.getPermissionType())
                    .startDate(a.getStartDate())
                    .endDate(a.getEndDate())
                    .approvalStatus(a.getApprovalStatus())
                    .build();
        }).collect(Collectors.toList());
    }
}
