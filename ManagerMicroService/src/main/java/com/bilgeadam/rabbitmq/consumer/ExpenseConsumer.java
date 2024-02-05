package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.CreateExpenseModel;
import com.bilgeadam.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseConsumer {

    private final ManagerService managerService;

    @RabbitListener(queues = "${rabbitmq.queue-expense-queue}")
    public void expenseQueue(CreateExpenseModel model){
        log.info("model{}",model.toString());
        managerService.createExpense(model);
    }
}
