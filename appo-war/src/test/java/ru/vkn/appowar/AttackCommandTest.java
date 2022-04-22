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

    @Test
    void canAttack1() throws OperationsException {
        data1();
        posititon = new PosititonLinear(10,
                technicalUnitSource.getUser(),
                technicalUnitTarget.getUser());
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        var result = fightCommand.can(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    void canAttack2() throws OperationsException {
        data1();
        posititon = new PosititonLinear(5,
                technicalUnitSource.getUser(),
                technicalUnitTarget.getUser());
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        posititon.moveForward(technicalUnitSource);
        posititon.moveForward(technicalUnitSource);
        var result = fightCommand.can(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        // then
        //TODO: assertThat(result).isEqualTo(true);
    }

    // ИТОГО -
    // (Огневая мощь 6)
    // * сноровка(1,75 - вероятность от 0 до 100 эффекта)
    // *(боевой дух от 0 до 1 нормировать) = 10
    // а ущерб пропорционально сноровке и равен = атака - броня 1 =
    @Test
    void actOnce() {
        // ущерб сноровка броня
        data1();
        var target = technicalUnitTarget.getUnits();
        fightCommand.act(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        // then
        // Аттака - в 50 проц случаев должна быть уменьшение ХП / 10 3 7
        assertThat(target.get(0).getHp()).isEqualTo(93);
        assertThat(target.get(1).getHp()).isEqualTo(93);
        assertThat(target.get(2).getHp()).isEqualTo(93);
    }

    @Test
    void actOnce2() {
        // боевой дух
        data2();
        var target = technicalUnitTarget.getUnits();
        fightCommand.act(
                technicalUnitSource,
                technicalUnitTarget,
                posititon);
        // then
        assertThat(target.get(0).getHp()).isEqualTo(93);
        assertThat(target.get(1).getHp()).isEqualTo(94);
        assertThat(target.get(2).getHp()).isEqualTo(93);
    }


    @Test
    void actOnce3() {
        UnitImplementation.RANDOM = true;
        // вероятность
        var hp = 0;
        for (int i = 0; i < 5000; i++) {
            data2();
            var target = technicalUnitTarget.getUnits();
            fightCommand.act(
                    technicalUnitSource,
                    technicalUnitTarget,
                    posititon);
            hp += target.get(0).getHp() > 93 ? 1 : 0;
        }
        assertThat(hp).isBetween(2000, 2600);
        UnitImplementation.RANDOM = false;
    }

    private void data1() {
        technicalUnitSource = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 7, 100, 15, 1),
                        new UnitImplementation(100, 3, 100, 15, 1)));
        technicalUnitTarget = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 10, 30, 1, 1),
                        new UnitImplementation(100, 10, 30, 1, 1),
                        new UnitImplementation(100, 10, 30, 1, 1)));
    }


    private void data2() {
        technicalUnitSource = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 7, 100, 15, 1),
                        new UnitImplementation(100, 3, 100, 15, 1)));
        technicalUnitTarget = new TacticalUnitImplementation(
                mock(User.class),
                List.of(
                        new UnitImplementation(100, 10, 30, 1, 1),
                        new UnitImplementation(100, 10, 30, 1, 3),
                        new UnitImplementation(100, 10, 30, 1, 1)));
    }
}