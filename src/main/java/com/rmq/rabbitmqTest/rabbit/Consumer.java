package com.rmq.rabbitmqTest.rabbit;

import com.rmq.rabbitmqTest.entity.Counter;
import com.rmq.rabbitmqTest.repository.CrudRedisRepository;
import com.rmq.rabbitmqTest.repository.RedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {
    private final RedisRepository repository;
    private final CrudRedisRepository crudRedisRepository;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consume1(Counter massage) {
        System.out.println("Rabbit massage: " + massage);
    }

    @RabbitListener(queues = "${rabbitmq.queue1}")
    public void consume2(Counter massage) throws InterruptedException {
      //  repository.save(massage);
        crudRedisRepository.save(massage);
        Thread.sleep(2000);
        System.out.println("Rabbit1 massage: " + massage);
      //  System.out.println("Redis massage: " + repository.findCounterById(massage.getCounter()));
        System.out.println("Redis massage: " + crudRedisRepository.findById(massage.getCounter()).orElseThrow());
        crudRedisRepository.save(massage);
        Thread.sleep(3000);
        System.out.println("Redis massage: " + crudRedisRepository.findById(massage.getCounter()).orElseThrow());
    }

}
