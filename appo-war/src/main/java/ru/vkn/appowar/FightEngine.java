package ru.vkn.appowar;

public class FightEngine {
    public void fight() {
        double j = 4;
        for(int i = 0; i < 10000; i++) {
            j = 1;
            for (int k = 0; k < 100000; k++) {
                for (int h = 0; h < 100000; h++) {
                    j *= i;
                }
            }
        }
    }
}