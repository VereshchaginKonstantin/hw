package ru.otus.hw;

public class ATMDemo {

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.push(DenominationType.ONE, 20);
        atm.push(DenominationType.FIFTY, 101);
        System.out.println(atm.get(50));
        System.out.println(atm.get(5001));
        try {
            System.out.println(atm.get(50));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        atm.push(DenominationType.ONE, 31);
        atm.push(DenominationType.HUNDRED, 1);
        var res = atm.get(50);
        System.out.println(res);
    }
}
