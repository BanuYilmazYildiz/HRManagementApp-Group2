package com.bilgeadam.manager;

import com.bilgeadam.dto.request.EmployeeCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(url = "http://localhost:9092/api/v1/employee", name = "user-employeeprofile")
public interface IEmployeeManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> createEmployee(@RequestBody EmployeeCreateRequestDto dto);

}