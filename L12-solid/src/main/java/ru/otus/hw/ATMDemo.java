package ru.otus.hw;

public class ATMDemo {

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.push(DenominationType.ONE, 20);
        atm.push(DenominationType.FIFTY, 300);
        atm.get(5800);
        atm.get(5800);
        // принимать банкноты разных номиналов
        // (на каждый номинал должна быть своя ячейка)
        //  выдавать запрошенную сумму минимальным количеством банкнот или ошибку
        //  если сумму нельзя выдать.
    }
}
