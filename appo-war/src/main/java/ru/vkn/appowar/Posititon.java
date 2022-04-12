package ru.vkn.appowar;

import javax.management.OperationsException;

public interface Posititon {
    long getDistance(TacticalUnit technicalUnitSource,
                     TacticalUnit technicalUnitTarget);
    void moveForward(TacticalUnit technicalUnitSource) throws OperationsException;

    void add(TacticalUnit technicalUnitSource) throws OperationsException;
}
