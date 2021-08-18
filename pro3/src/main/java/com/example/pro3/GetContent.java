package com.example.pro3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.pro3.fucntion.Pro3Function;
import com.example.pro3.service.Inputdata;
import com.example.pro3.service.WebService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class GetContent {

    
    @Autowired
    private Pro3Function r ;
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000) // every 5 sec
    public void reportTime(){
        System.out.println("The time is now " + dateFormat.format(new Date()));

        //Jedis jedis = new Jedis("localhost");

        List<String> messages = new ArrayList<>();
        Jedis j2 = r.conenct();
        messages = j2.blpop(0,"sanooknewslink");

        WebService web = new WebService();
        JSONObject detail = web.inputurl(messages.get(1));

        Inputdata input = new Inputdata();
        input.inputJSONObject(detail);

        for(int i=0; i<messages.size(); i++){
            j2.rpush("detailweb", detail.toString());
        }

        List<String> details = new ArrayList<>();
      
        System.out.println("Waiting for detail in queue");
        details = j2.blpop(0,"detailweb");
        System.out.println("detail received");
        System.out.println("Key:" + details.get(0) + " VALUE: " + details.get(1));
        
    }
    
}
