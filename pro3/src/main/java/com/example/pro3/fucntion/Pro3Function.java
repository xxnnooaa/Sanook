package com.example.pro3.fucntion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class Pro3Function {
    
    @Value("${redis.host}")
    private String redis_host;

    public Jedis conenct(){
        Jedis x = new Jedis(redis_host);
        return x;
    }
}
