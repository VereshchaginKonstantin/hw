package ru.otus.hw;

import java.util.Collections;
import java.util.List;

public class ATMSlot {

    long count;
    DenominationType type;

    public ATMSlot(DenominationType type) {
        this.type = type;
    }

    public List<Banknote> getLowerBound(long amount) {
        // TODO
        return Collections.emptyList();
    }

    public void push(long count) {
        this.count += count;
    }
}
