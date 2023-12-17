package com.saiprakash;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class CURLClient {

    private String protocol = "http";
    private String requestType = "GET";
    private HashMap<String, String> headers;
    private String data;
    private Boolean verbose;

    private URL url;

    public CURLClient(String url, String requestType, String data, String headers, Boolean verbose)
    throws MalformedURLException{
        this.data = data;
        this.url = new URL(url);
        this.requestType = requestType;
        this.headers = stringToMap(headers);
        this.verbose = verbose;

    }

    private HashMap<String,String> stringToMap(String s){
        JSONObject jsonObject = new JSONObject(s);
        HashMap<String,String> hashMap = new HashMap<>();
        jsonObject.toMap().forEach((k,v) -> hashMap.put(k,v.toString()));
        return hashMap;
    }

    private HttpURLConnection getConnection() throws IOException {
        return CURLConnection
                .builder(url)
                .setRequestType(requestType)
                .setRequestHeaders(headers).
                build().getHttpURLConnection();
    }

    public void printConnectionOutput() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getConnection().getInputStream()));
        String toPrint;
        while((toPrint=bufferedReader.readLine())!=null){
            System.out.println(toPrint);
        }
        bufferedReader.close();
    }

}
