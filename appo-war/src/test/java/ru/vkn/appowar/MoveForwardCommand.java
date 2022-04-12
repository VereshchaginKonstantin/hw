package ru.vkn.appowar;

public class MoveForwardCommand implements FightCommand {

    @Override
    public void act(TacticalUnit technicalUnitSource,
                    Posititon posititon) {
        posititon.moveForvard(technicalUnitSource);
    }
}
