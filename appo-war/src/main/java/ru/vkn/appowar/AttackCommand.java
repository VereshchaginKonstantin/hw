package ru.vkn.appowar;

public class AttackCommand implements BiFightCommand {
    @Override
    public void act(TacticalUnit unitSource, TacticalUnit unitTarget, Posititon posititon) {
        var fp = unitSource.getFirePower();
        for (var unit : unitTarget.getUnits()) {
            unit.setHp(unit.getHp() - fp + unit.getDefence());
        }
    }
}
