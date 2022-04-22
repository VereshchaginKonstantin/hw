package ru.vkn.appowar;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UnitImplementation {

    public static boolean RANDOM = false;

    int hp;
    int defence;
    int skill;
    int power;
    int spirit;

    public int getRealDefence() {
        return (int) Math.ceil(getDefence() * getCoef());
    }

    public int getRealPower() {
        return (int) Math.ceil(getPower() * getCoef());
    }

    private double getCoef() {
        return getSkillCoef()
                * getSpiritCoef()
                * getRandomCoef();
    }

    private double getSkillCoef() {
        return getSkill() / 100.0;
    }

    private double getRandomCoef() {
        if (RANDOM) {
            return  (100.0 + new Random().nextInt() % 50) / 100.0;
        } else {
            return 1.0;
        }
    }

    private double getSpiritCoef() {
        return 1.5 - 1.0 / (1.0 + (double) getSpirit());
    }
}
