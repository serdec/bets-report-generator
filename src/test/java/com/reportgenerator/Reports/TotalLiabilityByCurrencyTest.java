package com.reportgenerator.Reports;

import com.reportgenerator.Objects.Bet;
import com.reportgenerator.Objects.BetCurrency;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalLiabilityByCurrencyTest {


    @Nested
    class Liability {
        @Test
        void initialValue() {
            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            assertEquals(totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getTotalLiability(), BigDecimal.valueOf(0));
            assertEquals(totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.EUR).getTotalLiability(), BigDecimal.valueOf(0));
        }

        @Test
        void oneBet() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);

            int totalLiabilityComparison = totalLiabilityByCurrency.getCurrencyStatistics(b1.getCurrency()).getTotalLiability().compareTo(BigDecimal.valueOf(3));
            assertEquals(0, totalLiabilityComparison);
        }

        @Test
        void twoBets() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "GBP");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);
            totalLiabilityByCurrency.processBet(b2);

            int totalLiabilityComparison = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getTotalLiability().compareTo(BigDecimal.valueOf(9.28));
            assertEquals(0, totalLiabilityComparison);

        }

        @Test
        void twoBetsTwoCurrencies() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "EUR");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);
            totalLiabilityByCurrency.processBet(b2);

            int totalLiabilityComparisonGBP = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getTotalLiability().compareTo(BigDecimal.valueOf(3));
            assertEquals(0, totalLiabilityComparisonGBP);

            int totalLiabilityComparisonEUR = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.EUR).getTotalLiability().compareTo(BigDecimal.valueOf(6.28));
            assertEquals(0, totalLiabilityComparisonEUR);

        }
    }

    @Nested
    class Stake {
        @Test
        void initialValue() {
            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            assertEquals(totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getTotalStake(), BigDecimal.valueOf(0));
            assertEquals(totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.EUR).getTotalStake(), BigDecimal.valueOf(0));

        }

        @Test
        void oneBet() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);

            int totalLiabilityComparisonGBP = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getTotalStake().compareTo(BigDecimal.valueOf(0.5));
            assertEquals(0, totalLiabilityComparisonGBP);

        }

        @Test
        void twoBets() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "GBP");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);
            totalLiabilityByCurrency.processBet(b2);

            int totalLiabilityComparisonGBP = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getTotalStake().compareTo(BigDecimal.valueOf(3.64));
            assertEquals(0, totalLiabilityComparisonGBP);
        }

        @Test
        void twoBetsTwoCurrencied(){
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "EUR");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);
            totalLiabilityByCurrency.processBet(b2);

            int totalLiabilityComparisonGBP = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getTotalStake().compareTo(BigDecimal.valueOf(0.5));
            assertEquals(0, totalLiabilityComparisonGBP);

            int totalLiabilityComparisonEUR = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.EUR).getTotalStake().compareTo(BigDecimal.valueOf(3.14));
            assertEquals(0, totalLiabilityComparisonEUR);
        }
    }

    @Nested
    class NumberOfBets {

        @Test
        void numOfBetsInitialValue() {

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();

            int numberOfBetsGBP = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.GBP).getNumberOfBets();
            assertEquals(0, numberOfBetsGBP);

            int numberOfBetsEUR = totalLiabilityByCurrency.getCurrencyStatistics(BetCurrency.EUR).getNumberOfBets();
            assertEquals(0, numberOfBetsEUR);

        }

        @Test
        void oneBet() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);

            assertEquals(1, totalLiabilityByCurrency.getCurrencyStatistics(b1.getCurrency()).getNumberOfBets());

        }

        @Test
        void twoBets() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "GBP");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);
            totalLiabilityByCurrency.processBet(b2);

            assertEquals(2, totalLiabilityByCurrency.getCurrencyStatistics(b1.getCurrency()).getNumberOfBets());
        }

        @Test
        void twoBetsTwoCurrencies() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");
            Bet b2 = Bet.createBet("Bet-11", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(3.14), BigDecimal.valueOf(2.0), "EUR");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);
            totalLiabilityByCurrency.processBet(b2);

            assertEquals(1, totalLiabilityByCurrency.getCurrencyStatistics(b1.getCurrency()).getNumberOfBets());
            assertEquals(1, totalLiabilityByCurrency.getCurrencyStatistics(b2.getCurrency()).getNumberOfBets());

        }
        @Test
        void sameBetTwice() {
            Bet b1 = Bet.createBet("Bet-10", Long.parseLong("1489490156000"), 1, "My Fair Lady", BigDecimal.valueOf(0.5), BigDecimal.valueOf(6.0), "GBP");

            TotalLiabilityByCurrency totalLiabilityByCurrency = TotalLiabilityByCurrency.createTotalLiabilityByCurrency();
            totalLiabilityByCurrency.processBet(b1);
            totalLiabilityByCurrency.processBet(b1);

            assertEquals(1, totalLiabilityByCurrency.getCurrencyStatistics(b1.getCurrency()).getNumberOfBets());
        }
    }
}

