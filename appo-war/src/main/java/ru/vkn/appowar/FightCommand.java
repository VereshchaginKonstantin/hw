package ru.vkn.appowar;

public interface FightCommand {
    void act(TacticalUnit technicalUnitSource, Posititon posititon);
}
