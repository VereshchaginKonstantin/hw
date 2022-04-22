package ru.vkn.appowar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UnitImplementation {
    int hp;
    int defence;
    int skill;
    int power;
    int spirit;

    public double getRealDefence() {
        return getDefence() * getSkillCoef() * getSpiritCoef();
    }

    public double getRealPower() {
        return getPower() * getSkillCoef() * getSpiritCoef();
    }

    private double getSkillCoef() {
        return 1 - 10.0 / (10.0 + (double) getSkill());
    }

    private double getSpiritCoef() {
        return 1.5 - 1.0 / (1.0 + (double) getSpirit());
    }
}
