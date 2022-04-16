package ru.vkn.appowar;

import javax.management.OperationsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class AttackCommandTest {

    FightCommand fightCommand;
    Posititon posititon;
    TacticalUnit technicalUnitSource;
    TacticalUnit technicalUnitTarget;

    @BeforeEach
    void setUp() {
          fightCommand = new MoveBackwardCommand();
          posititon = mock(Posititon.class); // init position as 3
          technicalUnitSource = mock(TacticalUnit.class);
          technicalUnitTarget = mock(TacticalUnit.class);
    }

    @Test
    void actOnce() throws OperationsException {
        fightCommand.act(
                technicalUnitSource,
                posititon);

        // then
        // Сблизить ТС 1 с другими ТС на единицу
        // Оставить остальные ТС на прежнем расстоянии
        Mockito.verify(posititon, Mockito.times(1))
                .moveBackward(technicalUnitSource);
    }
}