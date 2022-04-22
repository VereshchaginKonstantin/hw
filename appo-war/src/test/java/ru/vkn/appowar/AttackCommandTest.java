package ru.vkn.appowar;

import java.util.List;

import javax.management.OperationsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    // ИТОГО -
    // (Огневая мощь 6)
    // * сноровка(1,75 - вероятность от 0 до 100 эффекта)
    // *(боевой дух от 0 до 1 нормировать) = 10
    // а ущерб пропорционально сноровке и равен = атака - броня 1 =
    @Test
    void actOnce() throws OperationsException {
        // TODO: 1. ущерб пропорционально сноровка
        // TODO: 2. боевой дух
        // TODO: 3. вероятность
        data1();
        var target = technicalUnitTarget.getUnits();
        fightCommand.act(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        // then
        // Аттака - в 50 проц случаев должна быть уменьшение ХП / 10 3 7
        assertThat(target.get(0).getHp()).isEqualTo(93);
        assertThat(target.get(1).getHp()).isEqualTo(97);
    }

    private void data1() {
        technicalUnitSource = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100L, 7L, 3L),
                        new UnitImplementation(100L, 3L, 7L)),
                10L);
        technicalUnitTarget = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100L, 0L, 3L),
                        new UnitImplementation(100L, 0L, 7L)),
                10L);
    }
}