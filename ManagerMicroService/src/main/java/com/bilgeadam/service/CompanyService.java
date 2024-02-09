package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateCompanyRequestDto;
import com.bilgeadam.dto.response.UpdateCompanyResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ManagerManagerException;
import com.bilgeadam.mapper.ICompanyMapper;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.IManagerRepository;
import com.bilgeadam.repository.entity.Company;
import com.bilgeadam.repository.entity.EStatus;
import com.bilgeadam.repository.entity.Manager;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import com.bilgeadam.utility.enums.ERole;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService extends ServiceManager<Company, String> {

    private final ICompanyRepository companyRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IManagerRepository managerRepository;
    public CompanyService(ICompanyRepository companyRepository, JwtTokenManager jwtTokenManager, IManagerRepository managerRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.jwtTokenManager = jwtTokenManager;

        this.managerRepository = managerRepository;
    }

    public Boolean createCompany(CreateCompanyRequestDto dto) {
//        Optional<Long> userId = jwtTokenManager.getIdFromToken(dto.getToken());
//        if (userId.isEmpty()) {
//            throw new ManagerManagerException(ErrorType.INVALID_TOKEN);}
//        Optional<Manager> manager = managerRepository.findOptionalByUserId(userId.get());
//        if (manager.isEmpty()){
//            throw new ManagerManagerException(ErrorType.MANAGER_NOT_FOUND);
//        }
//        if(manager.get().getRole().equals(ERole.ADMIN)){
//            Company company = ICompanyMapper.INSTANCE.toCompany(dto);
//            Optional<Company> company2 = companyRepository.findOptionalByCompanyName(dto.getCompanyName());
//            if (company2.isPresent()) {
//                throw new ManagerManagerException(ErrorType.COMPANY_ALREADY_EXIST);
//            }
//            try {
//                save(company);
//            } catch (Exception e) {
//                throw new ManagerManagerException(ErrorType.COMPANY_NOT_CREATED);
//            }
//            return true;
//        }
//        throw new ManagerManagerException(ErrorType.BAD_REQUEST);

        Company company = ICompanyMapper.INSTANCE.toCompany(dto);
            Optional<Company> company2 = companyRepository.findOptionalByCompanyName(dto.getCompanyName());
            if (company2.isPresent()) {
                throw new ManagerManagerException(ErrorType.COMPANY_ALREADY_EXIST);
            }
            try {
                save(company);
            } catch (Exception e) {
                throw new ManagerManagerException(ErrorType.COMPANY_NOT_CREATED);
            }
            return true;
    }

    public Company getDetailInfo(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new ManagerManagerException(ErrorType.COMPANY_NOT_FOUND);
        }
        return company.get();
    }

    public Company updateCompany(UpdateCompanyResponseDto dto) {
        Optional<Company> company = companyRepository.findById(dto.getId());
        if (company.isEmpty()) {
            throw new ManagerManagerException(ErrorType.COMPANY_NOT_FOUND);
        }
        company.get().setCompanyName(dto.getCompanyName());
        company.get().setEmail(dto.getEmail());
        company.get().setTitle(dto.getTitle());
        company.get().setPhone(dto.getPhone());
        company.get().setAddress(dto.getAddress());
        company.get().setFoundationYear(dto.getFoundationYear());
        company.get().setContractStartDate(dto.getContractStartDate());
        company.get().setContractFinishDate(dto.getContractFinishDate());
        update(company.get());
        return company.get();
    }

    public Boolean deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new ManagerManagerException(ErrorType.COMPANY_NOT_FOUND);
        }
        company.get().setStatus(EStatus.DELETED);
        update(company.get());
        return true;
    }

}