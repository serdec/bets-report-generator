package com.reportgenerator.Objects;

import java.util.*;
import java.util.stream.Collectors;

public class Selection {

    Map<Integer, CurrencyStatistics> statistics;

    public static Selection createSelection() {
        return new Selection();
    }

    private Selection() {
        this.statistics = new HashMap<>();
    }

    public CurrencyStatistics getCurrencyStatistics(Integer selectionId){
        return statistics.getOrDefault(selectionId, CurrencyStatistics.emptyStatistics());
    }

    public void processBet(Bet b) {
        CurrencyStatistics betCurrencyStatistics = statistics.computeIfAbsent(
                b.getSelectionId(),
                c-> CurrencyStatistics.createCurrencyStatistics(b.getSelectionId(), b.getSelectionName())
        );

       betCurrencyStatistics.processBet(b);
    }

    public  Map<Integer, CurrencyStatistics> getSortedStatistics(boolean ascendingOrder){

        List<Map.Entry<Integer, CurrencyStatistics>> list = new LinkedList<>(this.statistics.entrySet());

        list.sort((l1, l2) -> ascendingOrder ?
                l1.getValue().getTotalLiability().compareTo(l2.getValue().getTotalLiability()) :
                l2.getValue().getTotalLiability().compareTo(l1.getValue().getTotalLiability()));

        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    public Map<Integer, CurrencyStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<Integer, CurrencyStatistics> statistics) {
        this.statistics = statistics;
    }
}
