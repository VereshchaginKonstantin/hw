package ru.vkn.appowar;

public interface Posititon {
    long getDistance(TacticalUnit technicalUnitSource,
                     TacticalUnit technicalUnitTarget);
    void moveForward(TacticalUnit technicalUnitSource);

    void add(TacticalUnit technicalUnitSource);
}
