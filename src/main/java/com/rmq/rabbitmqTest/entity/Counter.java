package com.rmq.rabbitmqTest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@RedisHash("Counter")
public class Counter implements Serializable {
    @Id
    int counter;
    String massage;

}
