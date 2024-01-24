package com.bilgeadam.manager;

import com.bilgeadam.dto.request.CreateManagerRequestDto;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(url = "http://localhost:9094/api/v1/manager", name = "user-managerprofile")
public interface IManagerManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> createManager(@RequestBody CreateManagerRequestDto dto);
}
