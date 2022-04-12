package ru.vkn.appowar;

public interface BiFightCommand {
    void act(TacticalUnit unitSource,
             TacticalUnit unitTarget,
             Posititon posititon);
}
