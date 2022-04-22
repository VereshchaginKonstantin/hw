package ru.vkn.appowar;

import java.util.List;
import java.util.stream.Collectors;

public class AttackCommand implements BiFightCommand {
    @Override
    public void act(TacticalUnit unitSource, TacticalUnit unitTarget, Posititon posititon) {
        var fp = unitSource.getFirePower();
        var fpPerUnit = fp / unitTarget.getUnits().size();
        var units = unitTarget.getUnits()
                .stream().collect(Collectors.toList());
        for (var unit : units) {
                unit.setHp(unit.getHp()
                        - (int) (fpPerUnit)
                        + (int) (unit.getDefence() *
                        (1 - 10.0 / (10.0 + (double) unit.getSkill())))
                );
        }
    }

    private double getSum(List<UnitImplementation> unitTarget) {
        return unitTarget.stream().map(x -> x.getSkill())
                .mapToDouble(x -> x).sum();
    }
}
