package ru.otus.hw;



public record Banknote(Long count, DenominationType type) {
    public long getAmount() {
        return count * type.value;
    }
}
