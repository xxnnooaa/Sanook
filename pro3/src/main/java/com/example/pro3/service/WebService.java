package com.example.pro3.service;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebService {
    public JSONObject inputurl(String url){
        
        JSONObject sanook = new JSONObject();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements details = doc.select("#EntryReader_0");
            Elements titles = doc.select(".jsx-2761676397 .title");
            //Elements tag = doc.select(".jsx-545328869");
            //Elements links = tag.select("a[href]");
            Elements dates = doc.select(".jsx-1665670616");
            Elements date = dates.select("time[datetime]");
            /*Elements imgs = doc.select(".content-figure__img");
            Elements img = imgs.select("[src]");*/

            /*JSONArray images = new JSONArray();
            for(Element imglink : img){
                images.put(imglink.attr("src"));
            }
            
            JSONArray tags = new JSONArray();
            JSONArray tlink = new JSONArray();
            for(Element taglink : links){
                tlink.put(taglink.attr("href"));
                tags.put(taglink.text());
            }*/

            sanook.put("Title", titles.text());
            sanook.put("Detail", details.text());
            sanook.put("Time", date.attr("datetime"));
            //sanook.put("Image_link", images.toString(2));
            //sanook.put("Tag_link", tlink.toString(2));
            //sanook.put("Tag_name", tags.toString(2));


        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sanook;
    }
}
