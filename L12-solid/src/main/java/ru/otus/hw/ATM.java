package ru.otus.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ATM {
    private Map<DenominationType, ATMSlot> slots = new HashMap();

    public void push(DenominationType one, long count) {
        slots.computeIfAbsent(one, x -> new ATMSlot(x)).push(count);
    }

    public List<Banknote> get(long amount) {
        List<Banknote> banknotes = new ArrayList<>();
        for (DenominationType value : Arrays.stream(DenominationType.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())) {
            banknotes.add(slots
                        .computeIfAbsent(value, x -> new ATMSlot(x))
                        .getLowerBound(amount - size(banknotes)));
            if (amount - size(banknotes) == 0) {
                slots.values().forEach(x -> x.commit());
                return banknotes
                        .stream()
                        .filter(x -> x.count() != 0)
                        .collect(Collectors.toList());
            }
        }
        throw new RuntimeException("Not enough money");
    }

    private long size(List<Banknote> banknotes) {
        return banknotes.stream()
                .mapToLong(x -> x.getAmount())
                .sum();
    }
}

