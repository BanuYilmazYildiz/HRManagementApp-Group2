package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.UpdateAdvanceStatusModel;
import com.bilgeadam.rabbitmq.model.UpdatePermissionStatusModel;
import com.bilgeadam.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePermissionConsumer {

    private final EmployeeService employeeService;

    @RabbitListener(queues = "${rabbitmq.queue-update-permission-queue}")
    public void permissionQueue(UpdatePermissionStatusModel model){
        log.info("model{}",model.toString());
        employeeService.updatePermission(model);
    }
}
