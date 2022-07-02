package ru.otus.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ATMSlot {

    long count;
    private final DenominationType type;
    private final List<Banknote> notCommited = new ArrayList<>();

    public ATMSlot(DenominationType type) {
        this.type = type;
    }

    public void commit() {
        notCommited.forEach(x -> count -= x.count());
        notCommited.clear();
    }

    public void rollback() {
        notCommited.clear();
    }

    public Banknote getLowerBound(long amount) {
        var result = new Banknote(amount / type.value, type);
        if (count < amount / type.value) {
            result = new Banknote(count, type);
        }
        notCommited.add(result);
        return result;
    }

    public void push(long count) {
        this.count += count;
    }
}
