package com.reportgenerator.SourceParsers;

import com.reportgenerator.Objects.Bet;
import com.opencsv.CSVReader;
import com.reportgenerator.Reports.ReportsCreator;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CsvParser {

    public static void run() {
        ReportsCreator reportsCreator = ReportsCreator.createReportsCreator();

        List<Bet> bets = getBetsFromCSV();
        reportsCreator.processBets(bets);
    }

    private static List<Bet> getBetsFromCSV() {
        List<Bet> bets;
        try {
            CSVReader reader = getCSVReader();
            reader.readNext(); //skip headers
            bets = readCSVFile(reader);

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return bets;
    }

    private static CSVReader getCSVReader() {
        CSVReader reader = null;
        try {
            String csvFile = "./src/main/resources/bets.csv";
            reader = new CSVReader(new FileReader(csvFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }

    private static List<Bet> readCSVFile(CSVReader reader) {
        List<Bet> bets = new ArrayList<>();
        String[] line;
        try {
            while ((line = reader.readNext()) != null) {
                bets.add(Bet.createBet(
                        line[0],
                        Long.parseLong(line[1].trim()),
                        Integer.parseInt(line[2].trim()),
                        line[3].trim(),
                        BigDecimal.valueOf(Double.parseDouble(line[4].trim())),
                        BigDecimal.valueOf(Double.parseDouble(line[5].trim())),
                        line[6].trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bets;
    }


}
