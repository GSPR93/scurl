package com.saiprakash;

import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "scurl", description = "To send HTTP requests for the given [url]")
public class sCURLCommand implements Runnable{


    @CommandLine.Parameters(index = "0", description = "url to send request to")
    private String url;

    @CommandLine.Option(names = {"-v","--verbose"}, description = "Prints the request(>) and response(<) headers")
    private Boolean verbose = false;

    @CommandLine.Option(names = {"-X", "--method"}, description = "HTTP request method")
    private String requestType = "GET";

    @CommandLine.Option(names = {"-d", "--data"}, description = "data for PUT/POST requests")
    private String data="";

    @CommandLine.Option(names = {"-H", "--header"}, description = "Headers for the PUT/POST request")
    private String headers="{}";

    public static void main(String[] args) {
        new CommandLine(new sCURLCommand()).execute(args);
    }

    @Override
    public void run() {
        try {
            new CURLClient(url,requestType,data,headers,verbose).printConnectionOutput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
