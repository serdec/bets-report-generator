package com.reportgenerator.Objects;

import java.math.BigDecimal;

public class CurrencyStatistics {
    int id;
    String name;

    int numberOfBets;
    BigDecimal totalStake;
    BigDecimal totalLiability;

    public static CurrencyStatistics createCurrencyStatistics(int id, String name){
        return new CurrencyStatistics(id, name);
    }
    public static CurrencyStatistics emptyStatistics(){
        return new CurrencyStatistics(-1, "");
    }
    private CurrencyStatistics(int id, String name){
        this.id = id;
        this.name = name;

        this.totalStake = BigDecimal.valueOf(0);
        this.totalLiability = BigDecimal.valueOf(0);
    };

    public void processBet(Bet b) {

        updateLiability(b);
        updateStake(b);
        updateNumOfBets();
    }

    private void updateLiability(Bet b){
        BigDecimal betLiability = b.getPrice().multiply(b.getStake());
        addLiability(betLiability);
    }

    private void updateStake(Bet b){
        addStake(b.getStake());
    }

    private void updateNumOfBets(){
        addOneBet();
    }
    private  void addLiability(BigDecimal liability){
        this.totalLiability = this.totalLiability.add(liability);
    }
    private void addStake(BigDecimal stake){
        this.totalStake = this.totalStake.add(stake);
    }

    private void addOneBet(){
        this.numberOfBets = this.numberOfBets + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfBets() {
        return numberOfBets;
    }

    public void setNumberOfBets(int numberOfBets) {
        this.numberOfBets = numberOfBets;
    }

    public BigDecimal getTotalStake() {
        return totalStake;
    }

    public void setTotalStake(BigDecimal totalStake) {
        this.totalStake = totalStake;
    }

    public BigDecimal getTotalLiability() {
        return totalLiability;
    }

    public void setTotalLiability(BigDecimal totalLiability) {
        this.totalLiability = totalLiability;
    }
}
