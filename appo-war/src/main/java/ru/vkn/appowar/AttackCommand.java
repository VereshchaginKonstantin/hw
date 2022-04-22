package ru.vkn.appowar;

import java.util.List;
import java.util.stream.Collectors;

public class AttackCommand implements BiFightCommand {
    @Override
    public void act(TacticalUnit unitSource, TacticalUnit unitTarget, Posititon posititon) {
        var fp = unitSource.getFirePower();
        var units = unitTarget.getUnits()
                .stream().collect(Collectors.toList());
        for (var unit : units
                .stream().sorted(
                        (x,y) ->
                                (x.getSkill() > y.getSkill()) ? 1 :
                                        (x.getSkill() > y.getSkill())
                        ? -1 :0
                ).collect(Collectors.toList())) {
            var allSkill = getSum(units);
            var fpCurrent = fp * (1 - (unit.getSkill() / allSkill));
            units.remove(unit);
            fp -= fpCurrent;
            unit.setHp(unit.getHp() - fpCurrent + unit.getDefence());
        }
    }

    private double getSum(List<UnitImplementation> unitTarget) {
        return unitTarget.stream().map(x -> x.getSkill())
                .mapToDouble(x -> x).sum();
    }
}
