package com.bilgeadam.service;

import com.bilgeadam.config.CloudinaryConfig;
import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.*;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ManagerManagerException;
import com.bilgeadam.mapper.IAdvanceForManagerMapper;
import com.bilgeadam.mapper.IExpenseForManagerMapper;
import com.bilgeadam.mapper.IManagerMapper;
import com.bilgeadam.mapper.IPermissionForManagerMapper;
import com.bilgeadam.rabbitmq.model.*;
import com.bilgeadam.rabbitmq.producer.UpdateAdvanceProducer;
import com.bilgeadam.rabbitmq.producer.UpdateExpenseProducer;
import com.bilgeadam.rabbitmq.producer.UpdatePermissionProducer;
import com.bilgeadam.repository.IAdvanceForManagerRepository;
import com.bilgeadam.repository.IExpenseForManagerRepository;
import com.bilgeadam.repository.IManagerRepository;
import com.bilgeadam.repository.IPermissionForManagerRepository;
import com.bilgeadam.repository.entity.AdvanceForManager;
import com.bilgeadam.repository.entity.ExpenseForManager;
import com.bilgeadam.repository.entity.Manager;
import com.bilgeadam.repository.entity.PermissionForManager;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import com.bilgeadam.utility.enums.ERole;
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
public class ManagerService extends ServiceManager<Manager,String> {

    private final IManagerRepository managerRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CloudinaryConfig cloudinaryConfig;

   private final IExpenseForManagerRepository expenseForManagerRepository;
   private final IPermissionForManagerRepository permissionForManagerRepository;
   private final IAdvanceForManagerRepository advanceForManagerRepository;
   private final UpdateExpenseProducer updateExpenseProducer;
   private final UpdateAdvanceProducer updateAdvanceProducer;
   private final UpdatePermissionProducer updatePermissionProducer;

    public ManagerService(IManagerRepository managerRepository, JwtTokenManager jwtTokenManager, CloudinaryConfig cloudinaryConfig, IExpenseForManagerRepository expenseForManagerRepository, IPermissionForManagerRepository permissionForManagerRepository, IAdvanceForManagerRepository advanceForManagerRepository, UpdateExpenseProducer updateExpenseProducer, UpdateAdvanceProducer updateAdvanceProducer, UpdatePermissionProducer updatePermissionProducer) {
        super(managerRepository);
        this.managerRepository = managerRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.cloudinaryConfig = cloudinaryConfig;
        this.expenseForManagerRepository = expenseForManagerRepository;
        this.permissionForManagerRepository = permissionForManagerRepository;
        this.advanceForManagerRepository = advanceForManagerRepository;
        this.updateExpenseProducer = updateExpenseProducer;
        this.updateAdvanceProducer = updateAdvanceProducer;
        this.updatePermissionProducer = updatePermissionProducer;
    }


    public Boolean createManager(CreateManagerRequestDto dto) {
        try {
          //  save(IManagerMapper.INSTANCE.fromCreateRequestToManager(dto));
            Manager manager = IManagerMapper.INSTANCE.fromCreateRequestToManager(dto);
            save(manager);
            return true;
        } catch (Exception e){
            throw  new ManagerManagerException(ErrorType.MANAGER_NOT_CREATED);
        }
    }

    public ManagerFindByUserIdDetailResponseDto findManager(Long userId) {
        Optional<Manager> manager = managerRepository.findOptionalByUserId(userId);
        if (manager.isEmpty()){
            throw new ManagerManagerException(ErrorType.MANAGER_NOT_FOUND);
        }

       ManagerFindByUserIdDetailResponseDto responseDto = IManagerMapper.INSTANCE.fromManagerToFindByIdDetailDtoTo(manager.get());
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
            throw new ManagerManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Manager> employee = managerRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new ManagerManagerException(ErrorType.MANAGER_NOT_FOUND);
        }
        String url = imageUpload(dto.getPhoto());
        employee.get().setPhoto(url);
        update(employee.get());
        return true;
    }

    public Boolean updateUser(UpdateRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (userId.isEmpty()){
            throw  new ManagerManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Manager> employee = managerRepository.findOptionalByUserId(userId.get());
        if (employee.isEmpty()){
            throw new ManagerManagerException(ErrorType.BAD_REQUEST);
        }
        employee.get().setPhone(dto.getPhone());
        employee.get().setAddress(dto.getAddress());
        update(employee.get());
        return true;
    }

    public Boolean createExpense(CreateExpenseModel model) {
        try {
             ExpenseForManager expenseForManager = IExpenseForManagerMapper.INSTANCE.fromCreateExpenseModelToExpenseForManager(model);
             expenseForManagerRepository.save(expenseForManager);
            return true;
        } catch (Exception e){
            throw new ManagerManagerException(ErrorType.EXPENSE_NOT_CREATED);
        }
    }

    public List<ExpenseForManager> findAllExpense() {
        return expenseForManagerRepository.findAll();
    }

    public Boolean createPermission(CreatePermissionModel model) {
        try {
            PermissionForManager permissionForManager = IPermissionForManagerMapper.INSTANCE.fromCreatePermissionModelToExpenseForManager(model);
            permissionForManagerRepository.save(permissionForManager);
            return true;
        } catch (Exception e){
            throw new ManagerManagerException(ErrorType.PERMISSION_NOT_CREATED);
        }
    }

    public Boolean createAdvance(CreateAdvanceModel model) {
        try {
            AdvanceForManager advanceForManager = IAdvanceForManagerMapper.INSTANCE.fromCreateAdvanceModelToAdvanceForManager(model);
            advanceForManagerRepository.save(advanceForManager);
            return true;
        } catch (Exception e){
            throw new ManagerManagerException(ErrorType.ADVANCE_NOT_CREATED);
        }
    }

    public List<ExpenseListManagerResponseDto> findAllExpenseManager(String token) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(token);
        if (userId.isEmpty()) {
            throw new ManagerManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Manager> manager = managerRepository.findOptionalByUserId(userId.get());
        if (manager.isEmpty()) {
            throw new ManagerManagerException(ErrorType.MANAGER_NOT_FOUND);
        }
        return expenseForManagerRepository.findAllByCompany(manager.get().getCompany()).stream().map(a->{
            return ExpenseListManagerResponseDto.builder()
                    .id(a.getId())
                    .name(a.getName())
                    .surname(a.getSurname())
                    .expenseAmount(a.getExpenseAmount())
                    .expenseType(a.getExpenseType())
                    .dateOfRequest(a.getDateOfRequest())
                    .approvalStatus(a.getApprovalStatus())
                    .currency(a.getCurrency())
                    .userId(a.getUserId())
                    .expenseId(a.getExpenseId())
                    .dateOfResponse(a.getDateOfResponse())
                    .build();
        }).collect(Collectors.toList());
    }

    public Boolean updateStatusExpense(UpdateExpenseStatusRequestDto dto) {
        Optional<ExpenseForManager> expense = expenseForManagerRepository.findByExpenseId(dto.getExpenseId());
        if (expense.isEmpty()){
            throw new ManagerManagerException(ErrorType.BAD_REQUEST);
        }
        expense.get().setApprovalStatus(dto.getApprovalStatus());
        expense.get().setDateOfResponse(LocalDate.now());
        expenseForManagerRepository.save(expense.get());
        updateExpenseProducer.updateExpense(UpdateExpenseStatusModel.builder()
                        .approvalStatus(expense.get().getApprovalStatus())
                        .dateOfResponse(expense.get().getDateOfResponse())
                        .expenseId(expense.get().getExpenseId())
                .build());
        return true;
    }


    public Boolean updateStatusAdvance(UpdateAdvanceStatusRequestDto dto) {
        Optional<AdvanceForManager> advance = advanceForManagerRepository.findByAdvanceId(dto.getAdvanceId());
        if (advance.isEmpty()){
            throw new ManagerManagerException(ErrorType.BAD_REQUEST);
        }
        advance.get().setApprovalStatus(dto.getApprovalStatus());
        advance.get().setReplyDate(LocalDate.now());
        advanceForManagerRepository.save(advance.get());
        updateAdvanceProducer.updateAdvance(UpdateAdvanceStatusModel.builder()
                .approvalStatus(advance.get().getApprovalStatus())
                .replyDate(advance.get().getReplyDate())
                .advanceId(advance.get().getAdvanceId())
                .build());
        return true;
    }

    public List<AdvanceListManagerResponseDto> findAllAdvanceManager(String token) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(token);
        if (userId.isEmpty()) {
            throw new ManagerManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Manager> manager = managerRepository.findOptionalByUserId(userId.get());
        if (manager.isEmpty()) {
            throw new ManagerManagerException(ErrorType.MANAGER_NOT_FOUND);
        }
        return advanceForManagerRepository.findAllByCompany(manager.get().getCompany()).stream().map(a->{
            return AdvanceListManagerResponseDto.builder()
                    .id(a.getId())
                    .userId(a.getUserId())
                    .name(a.getName())
                    .surname(a.getSurname())
                    .amountOfRequest(a.getAmountOfRequest())
                    .dateOfRequest(a.getDateOfRequest())
                    .approvalStatus(a.getApprovalStatus())
                    .currency(a.getCurrency())
                    .userId(a.getUserId())
                    .advanceAmountWithSalary(a.getAdvanceAmountWithSalary())
                    .advanceId(a.getAdvanceId())
                    .replyDate(a.getReplyDate())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<PermissionListManagerResponseDto> findAllPermissionManager(String token) {
        Optional<Long> userId = jwtTokenManager.getIdFromToken(token);
        if (userId.isEmpty()) {
            throw new ManagerManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Manager> manager = managerRepository.findOptionalByUserId(userId.get());
        if (manager.isEmpty()) {
            throw new ManagerManagerException(ErrorType.MANAGER_NOT_FOUND);
        }
        return permissionForManagerRepository.findAllByCompany(manager.get().getCompany()).stream().map(a->{
            return PermissionListManagerResponseDto.builder()
                    .id(a.getId())
                    .userId(a.getUserId())
                    .name(a.getName())
                    .surname(a.getSurname())
                    .permissionId(a.getPermissionId())
                    .permissionType(a.getPermissionType())
                    .startDate(a.getStartDate())
                    .endDate(a.getEndDate())
                    .description(a.getDescription())
                    .dateOfRequest(a.getDateOfRequest())
                    .approvalStatus(a.getApprovalStatus())
                    .userId(a.getUserId())
                    .replyDate(a.getReplyDate())
                    .build();
        }).collect(Collectors.toList());
    }

    public Boolean updateStatusPermission(UpdatePermissionStatusRequestDto dto) {
        Optional<PermissionForManager> permission = permissionForManagerRepository.findByPermissionId(dto.getPermissionId());
        if (permission.isEmpty()){
            throw new ManagerManagerException(ErrorType.BAD_REQUEST);
        }
        permission.get().setApprovalStatus(dto.getApprovalStatus());
        permission.get().setReplyDate(LocalDate.now());
        permissionForManagerRepository.save(permission.get());
        updatePermissionProducer.updatePermission(UpdatePermissionStatusModel.builder()
                .approvalStatus(permission.get().getApprovalStatus())
                .replyDate(permission.get().getReplyDate())
                .permissionId(permission.get().getPermissionId())
                .build());
        return true;
    }

    public List<ManagerListResponseDto> findAllManager() {
        return managerRepository.findAllByRole(ERole.MANAGER).stream().map(x->{
            return ManagerListResponseDto.builder()
                    .userId(x.getUserId())
                    .name(x.getName())
                    .surname(x.getSurname())
                    .email(x.getEmail())
                    .company(x.getCompany())
                    .address(x.getAddress())
                    .photo(x.getPhoto())
                    .profession(x.getProfession())
                    .department(x.getDepartment())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<AdminListResponseDto> findAllAdmin() {
        return managerRepository.findAllByRole(ERole.ADMIN).stream().map(x->{
            return AdminListResponseDto.builder()
                    .userId(x.getUserId())
                    .name(x.getName())
                    .surname(x.getSurname())
                    .email(x.getEmail())
                    .address(x.getAddress())
                    .photo(x.getPhoto())
                    .build();
        }).collect(Collectors.toList());
    }
}
