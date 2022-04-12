package ru.vkn.appowar;

public interface Posititon {
    long getDistance(TacticalUnit technicalUnitSource,
                     TacticalUnit technicalUnitTarget);
    void moveForvard(TacticalUnit technicalUnitSource);

    void add(TacticalUnit technicalUnitSource);
}
