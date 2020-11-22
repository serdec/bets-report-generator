package com.reportgenerator.Reports;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigurationHandler {

    public enum Source {
        CSV,
        HTTP,
    }

    public enum Output {
        CSV,
        CONSOLE,
    }

    private Source source;
    private Output output;
    private static ConfigurationHandler configuration_instance;

    private ConfigurationHandler() {
        this.source = Source.CSV;
        this.output = Output.CONSOLE;
    }

    public static ConfigurationHandler getInstance() {
        if (configuration_instance == null)
            configuration_instance = new ConfigurationHandler();
        return configuration_instance;
    }

    public void getSourceFromUser() {
        String chosenSource = "";
        do {
            System.out.println("Do you want to read bets from the standard csv file or would you like to activate the http service?");
            System.out.println("Type 1 for csv source, 2 for http service (default 1)");

            chosenSource = readInputFromConsole();

            if(chosenSource.equals(""))
                this.source = Source.CSV;
            if (chosenSource.equals("1"))
                this.source = Source.CSV;
            if (chosenSource.equals("2"))
                this.source = Source.HTTP;

        } while (source != Source.CSV && source != Source.HTTP);
    }

    private String readInputFromConsole() {
        try {
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public void getOutputFromUser() {
        String chosenOutput = "";
        do {
            System.out.println("Where do you want your reports to be written?");
            System.out.println("Type 1 for console, 2 for csv file (default 1)");

            chosenOutput = readInputFromConsole();
        } while (output != Output.CSV && output != Output.CONSOLE);

        if(chosenOutput.equals(""))
            this.output = Output.CONSOLE;
        if (chosenOutput.equals("1"))
            this.output = Output.CONSOLE;
        if (chosenOutput.equals("2"))
            this.output = Output.CSV;
    }

    public Source getSource() {
        return source;
    }

    public Output getOutput() {
        return output;
    }
}
