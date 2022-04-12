package ru.vkn.appowar;

import javax.management.OperationsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PositionTest {

    Posititon posititon;
    TacticalUnit technicalUnitSource;
    TacticalUnit technicalUnitTarget;
    TacticalUnit technicalUnitTarget2;
    TacticalUnit technicalUnitFailed;

    @BeforeEach
    void setUp() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        posititon = new PosititonLinear(10L,
                user1,
                user2);
        technicalUnitSource = mock(TacticalUnit.class);
        technicalUnitTarget = mock(TacticalUnit.class);
        technicalUnitTarget2 = mock(TacticalUnit.class);
        technicalUnitFailed = mock(TacticalUnit.class);
        when(technicalUnitSource.getUser())
                .thenAnswer(x ->  user1);
        when(technicalUnitTarget.getUser())
                .thenAnswer(x ->  user2);
        when(technicalUnitTarget2.getUser())
                .thenAnswer(x ->  user2);
        when(technicalUnitFailed.getUser())
                .thenAnswer(x ->  mock(User.class));
    }

    @Test
    void add() throws OperationsException  {
        posititon.add(technicalUnitSource);
        Mockito.verify(technicalUnitSource,
                        Mockito.times(1))
                .getUser();
    }

    @Test
    void initFailed() throws OperationsException  {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        assertThrows(OperationsException.class,
                () -> posititon.add(technicalUnitFailed));
    }

    @Test
    void move1() throws OperationsException  {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        posititon.moveForward(technicalUnitSource);
        assertThat(posititon.getDistance(
                technicalUnitSource,
                technicalUnitTarget
        ))
                .isEqualTo(9L);
    }

    @Test
    void move2() throws OperationsException  {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        posititon.moveForward(technicalUnitTarget);
        assertThat(posititon.getDistance(
                technicalUnitSource,
                technicalUnitTarget
        ))
                .isEqualTo(9L);
    }

    @Test
    void move3() throws OperationsException  {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        posititon.moveForward(technicalUnitTarget);
        posititon.moveForward(technicalUnitSource);
        assertThat(posititon.getDistance(
                technicalUnitSource,
                technicalUnitTarget
        ))
                .isEqualTo(8L);
    }

    @Test
    void move4() throws OperationsException  {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        posititon.add(technicalUnitTarget2);
        posititon.moveForward(technicalUnitTarget);
        posititon.moveForward(technicalUnitSource);
        assertThat(posititon.getDistance(
                technicalUnitSource,
                technicalUnitTarget2
        ))
                .isEqualTo(9L);
    }

    @Test
    void move5() throws OperationsException  {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        posititon.add(technicalUnitTarget2);
        posititon.moveForward(technicalUnitTarget);
        posititon.moveForward(technicalUnitSource);
        assertThat(posititon.getDistance(
                technicalUnitTarget2,
                technicalUnitSource

        ))
                .isEqualTo(9L);
    }

    @Test
    void move6() throws OperationsException  {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        posititon.add(technicalUnitTarget2);
        posititon.moveForward(technicalUnitTarget);
        posititon.moveForward(technicalUnitSource);
        assertThat(posititon.getDistance(
                technicalUnitTarget2,
                technicalUnitTarget

        ))
                .isEqualTo(1L);
    }

    @Test
    void init() throws OperationsException {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);
        assertThat(posititon.getDistance(
                technicalUnitSource,
                technicalUnitTarget
        ))
        .isEqualTo(10L);
    }
}