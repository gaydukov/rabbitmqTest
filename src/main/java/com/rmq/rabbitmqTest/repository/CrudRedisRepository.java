package com.rmq.rabbitmqTest.repository;

import com.rmq.rabbitmqTest.entity.Counter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRedisRepository extends CrudRepository<Counter,Integer> {
}
