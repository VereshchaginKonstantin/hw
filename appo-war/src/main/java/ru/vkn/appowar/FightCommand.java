package ru.vkn.appowar;

public interface FightCommand {
    void act(TacticalUnit unitSource,
               TacticalUnit unitTarget,
               Posititon posititon);
}
