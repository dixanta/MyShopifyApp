/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.myshopify.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *
 * @author USER
 */
public class HttpClient {
    
    public static String get(String url)throws IOException{
        HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
        StringBuilder content;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line="";
            content = new StringBuilder();
            while((line=reader.readLine())!=null){
                content.append(line);
            }
        }
        return content.toString();
    }
    public static String post(String url,String params)throws IOException{
        HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line="";
        StringBuilder content=new StringBuilder();
        while((line=reader.readLine())!=null){
            content.append(line);
        }
        reader.close();
        return content.toString();
    }
}
