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

    public int getRealDefence() {
        return (int) Math.ceil(getDefence() * getSkillCoef() * getSpiritCoef());
    }

    public int getRealPower() {
        return (int) Math.ceil(getPower() * getSkillCoef() * getSpiritCoef());
    }

    private double getSkillCoef() {
        return getSkill() / 100.0;
    }

    private double getSpiritCoef() {
        return 1.5 - 1.0 / (1.0 + (double) getSpirit());
    }
}
