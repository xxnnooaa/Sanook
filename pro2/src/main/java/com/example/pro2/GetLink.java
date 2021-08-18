package com.example.pro2 ;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.pro2.function.Pro2Function;
import com.example.pro2.service.RssService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
    public class GetLink {


    @Autowired
    private Pro2Function r ;


        private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
        @Scheduled(fixedRate = 5000) // every 5 sec
        public void reportTime(){
            //System.out.println("The time is now " + dateFormat.format(new Date()));
            //Jedis jedis = new Jedis("localhost");

            Jedis j1 = r.conenct();
            List<String> messages = new ArrayList<>();

            messages = j1.blpop(0,"Category");
            
            JSONObject obj = new JSONObject(messages.get(1));
            for(int i=0; i<messages.size(); i++){

               RssService s = new RssService();
               JSONArray snlinks = s.inputlink(obj.getString("link"));
               System.out.println(snlinks.toString());

               for(int j=0; j<snlinks.length(); j++){
                    

                        String str = snlinks.get(j).toString();
                        String[] arrSplit = str.split("\\|");

                        if(arrSplit.length > 1){
                            System.out.println(arrSplit[1]);
                            j1.rpush("sanooknewslink", arrSplit[1].toString());
                        }
                        else {
                            System.out.println(arrSplit[1]);
                        }
               }
            }
            List<String> sanooks = new ArrayList<>();
        
            System.out.println("Waiting for detail in queue");
            sanooks = j1.blpop(0,"sanooknewslink");
            System.out.println("detail received");
            System.out.println("Key:" + sanooks.get(0) + " VALUE: " + sanooks.get(1));
    }    
}
 

