package com.bilgeadam.service;

import com.bilgeadam.config.CloudinaryConfig;
import com.bilgeadam.dto.request.CreateManagerRequestDto;
import com.bilgeadam.dto.request.ImageUploadRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.ManagerFindByUserIdDetailResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ManagerManagerException;
import com.bilgeadam.mapper.IManagerMapper;
import com.bilgeadam.repository.IManagerRepository;
import com.bilgeadam.repository.entity.Manager;
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
public class ManagerService extends ServiceManager<Manager,String> {

    private final IManagerRepository managerRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CloudinaryConfig cloudinaryConfig;

    public ManagerService(IManagerRepository managerRepository, JwtTokenManager jwtTokenManager, CloudinaryConfig cloudinaryConfig) {
        super(managerRepository);
        this.managerRepository = managerRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.cloudinaryConfig = cloudinaryConfig;
    }


    public Boolean createManager(CreateManagerRequestDto dto) {
        try {
          //  save(IManagerMapper.INSTANCE.fromCreateRequestToManager(dto));
            Manager manager = IManagerMapper.INSTANCE.fromCreateRequestToManager(dto);
            manager.setEmail(dto.getName()+"." +dto.getSurname()+"@"+dto.getCompany()+".com");
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
}
