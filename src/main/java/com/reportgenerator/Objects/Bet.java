package com.reportgenerator.Objects;

import java.math.BigDecimal;


public class Bet {


    private String betId;
    private long timestamp;
    private int selectionId;
    private String selectionName;
    private BigDecimal stake;
    private BigDecimal price;
    private BetCurrency currency;

    public static Bet createBet(
            String betId,
            long timestamp,
            int selectionId,
            String selectionName,
            BigDecimal stake,
            BigDecimal price,
            String currency){

        return new Bet(betId, timestamp, selectionId, selectionName, stake, price, currency);
    }
    private Bet(
            String betId,
            long timestamp,
            int selectionId,
            String selectionName,
            BigDecimal stake,
            BigDecimal price,
            String currency ){

        this.betId = betId;
        this.timestamp = timestamp;
        this.selectionId = selectionId;
        this.selectionName = selectionName;
        this.stake = stake;
        this.price = price;
        this.currency = BetCurrency.valueOf(currency);
    }

    // Needed for jackson deserialization
    private Bet(){
        this.betId = "";
        this.timestamp = -1;
        this.selectionId = -1;
        this.selectionName = "";
        this.stake = BigDecimal.valueOf(0);
        this.price = BigDecimal.valueOf(0);
        this.currency = BetCurrency.GBP;
    }

    public String getBetId() {
        return betId;
    }
    public void setBetId(String betId) {
        this.betId = betId;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getSelectionId() {
        return selectionId;
    }
    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public String getSelectionName() {
        return selectionName;
    }
    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStake() {
        return stake;
    }
    public void setStake(BigDecimal stake) {
        this.stake = stake;
    }

    public BetCurrency getCurrency() {
        return this.currency;
    }
    public void setCurrency(BetCurrency currency) {
        this.currency = currency;
    }
}
