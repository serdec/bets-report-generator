package com.reportgenerator.Reports;

import com.reportgenerator.Objects.Bet;
import com.reportgenerator.Objects.BetCurrency;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectionLiabilityByCurrencyTest {

    @Nested
    class Liability {
        @Test
        void initialValue() {
            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();

            int totalLiabilityComparison = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalLiability().compareTo(BigDecimal.valueOf(0));
            assertEquals(0, totalLiabilityComparison);

        }

        @Test
        void oneBet() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);

            int totalLiabilityComparison = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalLiability().compareTo(BigDecimal.valueOf(3));
            assertEquals(0, totalLiabilityComparison);

        }

        @Test
        void twoBets() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "GBP");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);
            selectionLiabilityByCurrency.processBet(b2);

            int res = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalLiability().compareTo(BigDecimal.valueOf(9.28));
            assertEquals(0, res);

        }
        @Test
        void twoBetsTwoCurrencies() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "EUR");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);
            selectionLiabilityByCurrency.processBet(b2);

            int totalLiabilityComparisonGBP = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalLiability().compareTo(BigDecimal.valueOf(3));
            assertEquals(0, totalLiabilityComparisonGBP);

            int totalLiabilityComparisonEUR = selectionLiabilityByCurrency.getSelection(BetCurrency.EUR).getCurrencyStatistics(1).getTotalLiability().compareTo(BigDecimal.valueOf(6.28));
            assertEquals(0, totalLiabilityComparisonEUR);

        }
    }

    @Nested
    class Stake{
        @Test
        void initialValue() {
            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();

            int totalStakeComparison = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalStake().compareTo(BigDecimal.valueOf(0));
            assertEquals(0, totalStakeComparison);

        }

        @Test
        void oneBet() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);

            int totalStakeComparison = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalStake().compareTo(BigDecimal.valueOf(0.5));
            assertEquals(0, totalStakeComparison);

        }

        @Test
        void twoBets() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "GBP");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);
            selectionLiabilityByCurrency.processBet(b2);

            int totalStakeComparison = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalStake().compareTo(BigDecimal.valueOf(3.64));
            assertEquals(0, totalStakeComparison);

        }
        @Test
        void twoBetsTwoCurrencies() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "EUR");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);
            selectionLiabilityByCurrency.processBet(b2);

            int totalStakeComparisonGBP = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getTotalStake().compareTo(BigDecimal.valueOf(0.5));
            assertEquals(0, totalStakeComparisonGBP);

            int totalStakeComparisonEUR = selectionLiabilityByCurrency.getSelection(BetCurrency.EUR).getCurrencyStatistics(1).getTotalStake().compareTo(BigDecimal.valueOf(3.14));
            assertEquals(0, totalStakeComparisonEUR);

        }
    }

    @Nested
    class NumberOfBets {

        @Test
        void initialValue(){
            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();

            int numberOfBets = selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getNumberOfBets();
            assertEquals(0, numberOfBets);
        }

        @Test
        void oneBet(){
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);

            assertEquals(1, selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getNumberOfBets());
        }
        @Test
        void twoBets(){
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "GBP");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);
            selectionLiabilityByCurrency.processBet(b2);

            assertEquals(2, selectionLiabilityByCurrency.getSelection(BetCurrency.GBP).getCurrencyStatistics(1).getNumberOfBets());
        }

        @Test
        void twoBetsTwoCurrencies(){
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "EUR");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);
            selectionLiabilityByCurrency.processBet(b2);

            assertEquals(1, selectionLiabilityByCurrency.getSelection(b1.getCurrency()).getCurrencyStatistics(1).getNumberOfBets());
            assertEquals(1, selectionLiabilityByCurrency.getSelection(b2.getCurrency()).getCurrencyStatistics(1).getNumberOfBets());
        }

        @Test
        void sameBetTwice(){
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");

            SelectionLiabilityByCurrency selectionLiabilityByCurrency = SelectionLiabilityByCurrency.createSelectionLiabilityByCurrency();
            selectionLiabilityByCurrency.processBet(b1);
            selectionLiabilityByCurrency.processBet(b1);

            assertEquals(1, selectionLiabilityByCurrency.getSelection(b1.getCurrency()).getCurrencyStatistics(1).getNumberOfBets());
        }
    }
}
