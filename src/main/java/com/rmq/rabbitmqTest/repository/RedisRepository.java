package com.rmq.rabbitmqTest.repository;

import com.rmq.rabbitmqTest.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class RedisRepository {
    public static final String HASH_KEY = "Counter";
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Counter save(Counter counter) {
        template.opsForHash().put(HASH_KEY, counter.getCounter(), counter);
        return counter;
    }

    public List<Counter> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Counter findCounterById(int id) {
        return (Counter) template.opsForHash().get(HASH_KEY, id);
    }


    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product removed !!";
    }

}
