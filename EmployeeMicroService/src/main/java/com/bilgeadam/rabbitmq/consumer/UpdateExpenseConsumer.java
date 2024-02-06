package com.bilgeadam.rabbitmq.consumer;


import com.bilgeadam.rabbitmq.model.UpdateExpenseStatusModel;
import com.bilgeadam.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateExpenseConsumer {
    private final EmployeeService employeeService;

    @RabbitListener(queues = "${rabbitmq.queue-update-expense-queue}")
    public void expenseQueue(UpdateExpenseStatusModel model){
        log.info("model{}",model.toString());
        employeeService.updateExpense(model);
    }
}
