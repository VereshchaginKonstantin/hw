package ru.vkn.appowar;

public interface FightCommand {
    void fight(TacticalUnit unitSource,
               TacticalUnit unitTarget,
               Posititon posititon);
}
