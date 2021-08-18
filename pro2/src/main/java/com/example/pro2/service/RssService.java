package com.example.pro2.service;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RssService {

    public JSONArray inputlink(String rss){

        JSONArray urls = new JSONArray();

        try {
            
            Document document = Jsoup.connect(rss).get();  
            
            Elements items = document.select("item");
            Elements titles = document.select("item title");
            Elements pubDates = document.select("item pubDate");
            Elements guids = document.select("item guid");
            Elements comments = document.select("item comments");
            Elements descriptions = document.select("item description");
            Elements links = document.select("item link");

            for(int i=0; i<items.size(); i++){

                urls.put(links.get(i).text());

                JSONObject item = new JSONObject();
                item.put("rss", rss);
                item.put("title", titles.get(i).text());
                item.put("link", links.get(i).text());
                item.put("guid", guids.get(i).text());
                item.put("comment", comments.get(i).text());
                item.put("pubDate", pubDates.get(i).text());
                item.put("description", descriptions.get(i).text());

                // Inputdata rssobj = new Inputdata();
                // rssobj.inputRssObj(item);
                
            }                    

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
         return urls;
    }
}

