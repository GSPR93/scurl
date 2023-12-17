package com.saiprakash;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

public class CURLConnection {
    private HttpURLConnection httpURLConnection;

    public CURLConnection(URLConnectionBuilder urlConnectionBuilder){
        this.httpURLConnection = urlConnectionBuilder.httpURLConnection;
    }

    public static URLConnectionBuilder builder(URL url) throws IOException {
        return new URLConnectionBuilder(url);
    }

    public HttpURLConnection getHttpURLConnection(){
        return httpURLConnection;
    }


    public static class URLConnectionBuilder{
        HttpURLConnection httpURLConnection;
        public URLConnectionBuilder(URL url) throws IOException {
            this.httpURLConnection = (HttpURLConnection) url.openConnection();
        }

        public URLConnectionBuilder setRequestType(String requestType) throws ProtocolException {
            httpURLConnection.setRequestMethod(requestType);
            return this;
        }

        public URLConnectionBuilder setRequestHeaders(HashMap<String,String> headers){
            headers.forEach((k,v) -> httpURLConnection.setRequestProperty(k,v));
            return this;
        }

        public CURLConnection build(){
            return new CURLConnection(this);
        }

    }
}
