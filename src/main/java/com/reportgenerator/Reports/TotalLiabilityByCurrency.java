package com.reportgenerator.Reports;

import com.reportgenerator.Objects.Bet;
import com.reportgenerator.Objects.BetCurrency;
import com.reportgenerator.Objects.CurrencyStatistics;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

public class TotalLiabilityByCurrency {
    Set<String> processedBets;
    Map<BetCurrency, CurrencyStatistics> statistics;

    public static TotalLiabilityByCurrency createTotalLiabilityByCurrency() {
        return new TotalLiabilityByCurrency();
    }

    private TotalLiabilityByCurrency() {
        this.statistics = new HashMap();
        this.processedBets = new HashSet<>();
    }

    public void processBet(Bet b) {

        if (alreadyProcessed(b.getBetId()))
            return;

        statistics.computeIfAbsent(
                b.getCurrency(),
                c -> CurrencyStatistics.createCurrencyStatistics(b.getSelectionId(), b.getSelectionName())
        ).processBet(b);

        processedBets.add(b.getBetId());
    }

    private boolean alreadyProcessed(String betId) {
        return processedBets.contains(betId);
    }

    public CurrencyStatistics getCurrencyStatistics(BetCurrency currency) {
        return statistics.getOrDefault(currency, CurrencyStatistics.emptyStatistics());
    }

    public void print() {
        System.out.println("\nTotal Liability Report By Currency\n");
        System.out.format("%11s%10s%17s%20s%n", "CURRENCY | ", "NUM BETS | ",
                "TOTAL STAKES | ", "TOTAL LIABILITIES");

        for (BetCurrency betCurrency : statistics.keySet()) {
            CurrencyStatistics currencyStatistics = statistics.get(betCurrency);
            System.out.format("%11s%11s%17s%20s%n",
                    betCurrency + " | ",
                    currencyStatistics.getNumberOfBets() + " | ",
                    Currency.getInstance(betCurrency.toString()).getSymbol() + currencyStatistics.getTotalStake() + " | ",
                    Currency.getInstance(betCurrency.toString()).getSymbol() + currencyStatistics.getTotalLiability());
        }
        System.out.println("\n\n");
    }
    public void printToCSV(){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("total-liability-by-currency-report.csv"), "utf-8"))) {
            writer.write("CURRENCY,NUM BETS,TOTAL STAKES,TOTAL LIABILITIES\n");

            for (BetCurrency betCurrency : statistics.keySet()) {
                CurrencyStatistics currencyStatistics = statistics.get(betCurrency);
                writer.write(
                        betCurrency + "," +
                                currencyStatistics.getNumberOfBets() + "," +
                                Currency.getInstance(betCurrency.toString()).getSymbol() + currencyStatistics.getTotalStake() + "," +
                                Currency.getInstance(betCurrency.toString()).getSymbol() + currencyStatistics.getTotalLiability() + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
