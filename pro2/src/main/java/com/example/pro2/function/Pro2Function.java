package com.example.pro2.function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class Pro2Function {
    
    @Value("${redis.host}")
    private String redis_host;

    public Jedis conenct(){
        Jedis x = new Jedis(redis_host);
        return x;
    }
}
