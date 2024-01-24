package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateManagerRequestDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ManagerManagerException;
import com.bilgeadam.mapper.IManagerMapper;
import com.bilgeadam.repository.IManagerRepository;
import com.bilgeadam.repository.entity.Manager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ManagerService extends ServiceManager<Manager,String> {

    private final IManagerRepository managerRepository;

    public ManagerService(IManagerRepository managerRepository) {
        super(managerRepository);
        this.managerRepository = managerRepository;
    }


    public Boolean createManager(CreateManagerRequestDto dto) {
        try {
            save(IManagerMapper.INSTANCE.fromCreateRequestToManager(dto));
            return true;
        } catch (Exception e){
            throw  new ManagerManagerException(ErrorType.MANAGER_NOT_CREATED);
        }
    }
}
