package com.rmq.rabbitmqTest.rabbit;

import com.rmq.rabbitmqTest.entity.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producer implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingkey}")
    private String routingKey;
    @Value("${rabbitmq.routingkey1}")
    private String routingKey1;
    private static int count;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            count++;
            System.out.println("counter " + count);
            rabbitTemplate.convertAndSend(exchange, routingKey, new Counter(count,"counter1 "));
            rabbitTemplate.convertAndSend(exchange, routingKey1, new Counter(count,"counter2 "));
         //   Thread.sleep(1000);
        }
    }

}
