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
                .sorted(Comparator.comparingLong(x -> x.value))
                .collect(Collectors.toList())) {
            banknotes.addAll(slots
                        .get(value)
                        .getLowerBound(amount));
            if (amount - size(banknotes) == 0) {
                return banknotes;
            }
        }
        throw new RuntimeException();
    }

    private long size(List<Banknote> banknotes) {
        return banknotes.stream()
                .map(x -> x.count() * x.type().value)
                .reduce((x, y) -> x + x)
                .get();
    }
}

