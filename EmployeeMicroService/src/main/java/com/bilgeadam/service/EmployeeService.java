package com.bilgeadam.service;

import com.bilgeadam.config.CloudinaryConfig;
import com.bilgeadam.dto.request.CreatePermissionRequestDto;
import com.bilgeadam.dto.request.EmployeeCreateRequestDto;
import com.bilgeadam.dto.request.EmployeeUpdateRequestDto;
import com.bilgeadam.dto.response.EmployeeFindByUserIdDetailResponseDto;
import com.bilgeadam.exception.EmployeeManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.mapper.IEmployeeMapper;
import com.bilgeadam.mapper.IPermissionMapper;
import com.bilgeadam.repository.EmployeeRepository;
import com.bilgeadam.repository.PermissionRepository;
import com.bilgeadam.repository.entity.Employee;
import com.bilgeadam.repository.entity.Permission;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService extends ServiceManager<Employee,String> {

    private final EmployeeRepository employeeRepository;
    private final PermissionRepository permissionRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CloudinaryConfig cloudinaryConfig;

    public EmployeeService(EmployeeRepository employeeRepository, PermissionRepository permissionRepository, JwtTokenManager jwtTokenManager, CloudinaryConfig cloudinaryConfig) {
        super(employeeRepository);
        this.employeeRepository=employeeRepository;
        this.permissionRepository = permissionRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.cloudinaryConfig = cloudinaryConfig;
    }

    public Boolean updateUser(EmployeeUpdateRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()){
            throw  new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.BAD_REQUEST); //TODO
        }
        employee.get().setPhone(dto.getPhone());
        employee.get().setAddress(dto.getAddress());

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

    public String updateImage(MultipartFile file, String token) {
        Long userId = jwtTokenManager.getIdFromToken(token).get();
        Optional<Employee> user = employeeRepository.findOptionalByUserId(userId);
        if (user.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        String url = imageUpload(file);
        return url;
    }

    public Boolean createPermission(CreatePermissionRequestDto dto){
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()) throw new EmployeeManagerException(ErrorType.INVALID_TOKEN);
        Optional<Employee> employee = employeeRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new EmployeeManagerException(ErrorType.EMPLOYEE_NOT_CREATED);
        }
        Permission permission = IPermissionMapper.INSTANCE.fromCreatePermissionRequestDto(dto);
//                .builder()
//                .epermissionType(dto.getEpermissionType())
//                .dateOfRequest(LocalDate.now())
//                .nameEmployee(employee.get().getName())
//                .surnameEmployee(employee.get().getSurname())
//                .userId(employee.get().getUserId())
//                .startDate(dto.getStartDate())
//                .endDate(dto.getEndDate())
//                .approvalStatus(PENDING_APPROVAL)
//                .companyName(employee.get().getCompanyName())
//                .build();
        permissionRepository.save(permission);
        return true;
    }

//    public Object updateStatusPermission(UpdateStatusRequestDto dto) {
//    }
}
