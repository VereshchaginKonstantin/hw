package ru.vkn.appowar;

public interface BiFightCommand {
    void act(TacticalUnit unitSource,
             TacticalUnit unitTarget,
             Posititon posititon);

    boolean can(TacticalUnit technicalUnitSource,
             TacticalUnit technicalUnitTarget,
             Posititon posititon);
}
