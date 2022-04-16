package ru.vkn.appowar;

import java.util.List;

import javax.management.OperationsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AttackCommandTest {

    BiFightCommand fightCommand;
    Posititon posititon;
    TacticalUnit technicalUnitSource;
    TacticalUnit technicalUnitTarget;

    @BeforeEach
    void setUp() {
          fightCommand = new AttackCommand();
          posititon = mock(Posititon.class); // init position as 3
          technicalUnitSource = mock(TacticalUnit.class);
          technicalUnitTarget = mock(TacticalUnit.class);
    }

    @Test
    void actOnce() throws OperationsException {
        List<Unit> start = technicalUnitTarget.getUnits();
        double hp = start.get(0).getHp();
        fightCommand.act(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        double firePower = technicalUnitSource.getFirePower();

        List<Unit> end = technicalUnitTarget.getUnits();
        double hpEnd = start.get(0).getHp();
        // then
        // Аттака - в 50 проц случаев должна быть уменьшение ХП
        assertThat(hpEnd - hp).isEqualTo(firePower);
    }
}