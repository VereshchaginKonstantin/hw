package ru.vkn.appowar;

import javax.management.OperationsException;

public class MoveForwardCommand implements FightCommand {

    @Override
    public void act(TacticalUnit technicalUnitSource,
                    Posititon posititon) {
        try {
            posititon.moveForward(technicalUnitSource);
        } catch (OperationsException e) {
            e.printStackTrace();
        }
    }
}
