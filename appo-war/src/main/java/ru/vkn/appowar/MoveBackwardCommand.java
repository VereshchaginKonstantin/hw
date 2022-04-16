package ru.vkn.appowar;

import javax.management.OperationsException;

public class MoveBackwardCommand implements FightCommand {

    @Override
    public void act(TacticalUnit technicalUnitSource,
                    Posititon posititon) {
        try {
            posititon.moveBackward(technicalUnitSource);
        } catch (OperationsException e) {
            e.printStackTrace();
        }
    }
}
