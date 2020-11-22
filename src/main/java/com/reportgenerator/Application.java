package com.reportgenerator;

import com.reportgenerator.Reports.ConfigurationHandler;
import com.reportgenerator.SourceParsers.CsvParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    static ConfigurationHandler configurationHandler;
    public static void main(String[] args) {

        configurationHandler = ConfigurationHandler.getInstance();

         /*
          * Uncomment the following line
          * if the user should choose the bets source at runtime
          */
//         configurationHandler.getSourceFromUser();

        /*
         * Uncomment the following line
         * if the user should choose the reports output at runtime
         */
//         configurationHandler.getOutputFromUser();

        if (configurationHandler.getSource() == ConfigurationHandler.Source.CSV)
            CsvParser.run();

        if (configurationHandler.getSource() == ConfigurationHandler.Source.HTTP)
            SpringApplication.run(Application.class, args);

    }
}


