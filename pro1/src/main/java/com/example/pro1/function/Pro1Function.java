package com.example.pro1.function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class Pro1Function {
    
    @Value("${redis.host}")
    private String redis_host;

    public Jedis conenct(){
        Jedis x = new Jedis(redis_host);
        return x;
    }
}
