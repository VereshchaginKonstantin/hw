package ru.vkn.appowar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PosititonTest {

    Posititon posititon;
    TacticalUnit technicalUnitSource;
    TacticalUnit technicalUnitTarget;

    @BeforeEach
    void setUp() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        posititon = new PosititonLinear(10L,
                user1,
                user2);
        technicalUnitSource = mock(TacticalUnit.class);
        technicalUnitTarget = mock(TacticalUnit.class);
        when(technicalUnitSource.getUser())
                .thenAnswer(x ->  user1);
        when(technicalUnitTarget.getUser())
                .thenAnswer(x ->  user2);
    }

    @Test
    void add() {
        posititon.add(technicalUnitSource);
        Mockito.verify(technicalUnitSource,
                        Mockito.times(1))
                .getUser();
    }

    @Test
    void init() {
        posititon.add(technicalUnitSource);
        posititon.add(technicalUnitTarget);

        assertThat(posititon.getDistance(
                technicalUnitSource,
                technicalUnitTarget
        ))
        .isEqualTo(10L);
    }
}