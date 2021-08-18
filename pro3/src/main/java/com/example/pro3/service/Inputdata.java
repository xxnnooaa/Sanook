package com.example.pro3.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class Inputdata {
      
    public void inputJSONObject(JSONObject obj){

        try {
            HttpResponse<String> jsonresponse = Unirest.post("http://localhost:9200/web3pro/_doc/")
            .header("Content-Type", "application/json")
            .body(obj.toString())
            .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

   /* public void inputRssObj(JSONObject rssobj){
        try {
            HttpResponse<String> jsonresponse = Unirest.post("http://localhost:9200/variety_rss/_doc/")
            .header("Content-Type", "application/json")
            .body(rssobj.toString())
            .asString();
        } 
        catch (UnirestException e) {
            e.printStackTrace();
        
    }*/
}
