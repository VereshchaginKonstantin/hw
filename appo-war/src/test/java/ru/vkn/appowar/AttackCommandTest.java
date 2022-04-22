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
        // ущерб сноровка броня(+)
        data1();
        var target = technicalUnitTarget.getUnits();
        fightCommand.act(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        // then
        // Аттака - в 50 проц случаев должна быть уменьшение ХП / 10 3 7
        assertThat(target.get(0).getHp()).isEqualTo(95);
        assertThat(target.get(1).getHp()).isEqualTo(95);
        assertThat(target.get(2).getHp()).isEqualTo(95);
    }

    @Test
    void actOnce2() throws OperationsException {
        // TODO: 2. боевой дух
        // TODO: 3. вероятность
        data2();
        var target = technicalUnitTarget.getUnits();
        fightCommand.act(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        // then
        assertThat(target.get(0).getHp()).isEqualTo(94);
        assertThat(target.get(1).getHp()).isEqualTo(92);
        assertThat(target.get(2).getHp()).isEqualTo(92);
    }

    private void data1() {
        technicalUnitSource = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 7, 10, 15, 1),
                        new UnitImplementation(100, 3, 10, 15, 1)));
        technicalUnitTarget = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 10, 7, 1, 1),
                        new UnitImplementation(100, 10, 3, 1, 1),
                        new UnitImplementation(100, 10, 1, 1, 1)));
    }


    private void data2() {
        technicalUnitSource = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 7, 3, 1, 1),
                        new UnitImplementation(100, 3, 7, 1, 1)));
        technicalUnitTarget = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 10, 7, 1, 1),
                        new UnitImplementation(100, 10, 3, 1, 1),
                        new UnitImplementation(100, 10, 1, 1, 1)));
    }
}