package ru.otus.hw;

public enum DenominationType {

    ONE(1),
    FIVE(5),
    FIFTY(50),
    HUNDRED(100);

    int value;

    DenominationType(int i) {
        value = i;
    }
}
