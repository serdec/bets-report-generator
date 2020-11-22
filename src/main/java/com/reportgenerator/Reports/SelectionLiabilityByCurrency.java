package com.reportgenerator.Reports;

import com.reportgenerator.Objects.Bet;
import com.reportgenerator.Objects.BetCurrency;
import com.reportgenerator.Objects.CurrencyStatistics;
import com.reportgenerator.Objects.Selection;

import java.io.*;
import java.util.*;


public class SelectionLiabilityByCurrency {

    Set<String> processedBets;
    Map<BetCurrency, Selection> selections;

    public static SelectionLiabilityByCurrency createSelectionLiabilityByCurrency() {
        return new SelectionLiabilityByCurrency();
    }

    private SelectionLiabilityByCurrency() {
        this.processedBets = new HashSet<>();
        this.selections = new HashMap();
    }

    public void processBet(Bet b) {
        if (alreadyProcessed(b.getBetId()))
            return;

        selections.computeIfAbsent(
                b.getCurrency(),
                c -> Selection.createSelection()
        ).processBet(b);
        processedBets.add(b.getBetId());
    }

    private boolean alreadyProcessed(String betId) {
        return processedBets.contains(betId);
    }

    public Selection getSelection(BetCurrency currency) {
        return selections.getOrDefault(currency, Selection.createSelection());
    }

    public void print() {
        System.out.println("\nSelection Liability Report By Currency\n");
        System.out.format("%22s%11s%11s%15s%18s%n",
                "SELECTION NAME | ", "CURRENCY | ", "NUM BETS | ",
                "TOTAL STAKES | ", "TOTAL LIABILITIES");
        for (BetCurrency currency : selections.keySet()) {
            Selection selection = selections.get(currency);
            Map<Integer, CurrencyStatistics> sortedStatistics = selection.getSortedStatistics(false);
            for (int selectionId : sortedStatistics.keySet()) {
                CurrencyStatistics currencyStatistics = selections.get(currency).getCurrencyStatistics(selectionId);
                System.out.format("%22s%11s%11s%15s%18s%n",
                        selections.get(currency).getCurrencyStatistics(selectionId).getName() + " | ",
                        currency + " | ",
                        currencyStatistics.getNumberOfBets() + " | ",
                        Currency.getInstance(currency.toString()).getSymbol() + currencyStatistics.getTotalStake() + " | ",
                        Currency.getInstance(currency.toString()).getSymbol() + currencyStatistics.getTotalLiability());
            }
        }
        System.out.println("\n\n");

    }
    public void printToCSV() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("selection-liability-by-currency-report.csv"), "utf-8"))) {

            writer.write("SELECTION NAME,CURRENCY,NUM BETS,TOTAL STAKES,TOTAL LIABILITIES\n");
            for (BetCurrency currency : selections.keySet()) {
                Selection selection = selections.get(currency);
                Map<Integer, CurrencyStatistics> sortedStatistics = selection.getSortedStatistics(false);
                for (int selectionId : sortedStatistics.keySet()) {
                    CurrencyStatistics currencyStatistics = selections.get(currency).getCurrencyStatistics(selectionId);
                    writer.write(
                            selections.get(currency).getCurrencyStatistics(selectionId).getName() + "," +
                                    currency + "," +
                                    currencyStatistics.getNumberOfBets() + "," +
                                    Currency.getInstance(currency.toString()).getSymbol() + currencyStatistics.getTotalStake() + "," +
                                    Currency.getInstance(currency.toString()).getSymbol() + currencyStatistics.getTotalLiability() + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
