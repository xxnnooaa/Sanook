package com.example.pro1.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.example.pro1.function.Pro1Function;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class GetRSS {

    @Autowired
    private Pro1Function r ;
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000) // every 5 sec
    public void reportTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        
        HashSet<String> duplicaterss = new HashSet<String>();
        try {

            Document doc = Jsoup.connect("https://money.sanook.com/rss/").get();
            Elements category = doc.select(".RSS-fonts a[title]");
            Elements links = category.select("a[title]");

            //Jedis jedis = new Jedis("localhost");
            Jedis j = r.conenct();
            JSONObject categorylink = new JSONObject();

            for (Element clink : links) {
                if (clink.attr("href").charAt(0) == '#' || clink.text().isEmpty()) {
                    continue;
                } else {

                    categorylink.put("link", clink.attr("href"));
                    categorylink.put("category", clink.text());
                    duplicaterss.add(categorylink.toString());
                }
            }
            Iterator<String> it = duplicaterss.iterator();
            while (it.hasNext()) {
                String element = it.next();
                System.out.println(element);
                j.rpush("Category", element);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
