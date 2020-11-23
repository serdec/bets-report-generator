package com.reportgenerator.Reports;

import com.reportgenerator.Objects.Bet;

import java.util.List;

public class ReportsCreator {

    ConfigurationHandler configurationHandler;

    public static ReportsCreator createReportsCreator(){
        return new ReportsCreator();
    }
    private ReportsCreator(){
        configurationHandler = ConfigurationHandler.getInstance();
    }
    public void processBets(List<Bet> bets){

        SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
        TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();

        bets.forEach(b -> {
            selectionLiabilityByCurrency.processBet(b);
            totalLiabilityByCurrency.processBet(b);
        });

        if(configurationHandler.getOutput() == ConfigurationHandler.Output.CONSOLE) {
            selectionLiabilityByCurrency.print();
            totalLiabilityByCurrency.print();
        }
        else {
            selectionLiabilityByCurrency.printToCSV();
            totalLiabilityByCurrency.printToCSV();
        }
    }
}
