package com.saiprakash;

import picocli.CommandLine;

@CommandLine.Command(name = "helloWord", description = "prints hello [name]")
public class HelloWorldCommand implements Runnable {

    @CommandLine.Parameters(index = "0", description = "name to print")
    private String name;

    @CommandLine.Option(names = {"-c","--caps"}, description = "to capitalize the name")
    private Boolean caps = false;

    public static void main(String[] args) {
        new CommandLine(new HelloWorldCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Hello " + (caps?name.toUpperCase():name));
    }
}
