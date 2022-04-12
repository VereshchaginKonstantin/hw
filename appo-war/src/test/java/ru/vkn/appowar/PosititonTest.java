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
        posititon = new PosititonLinear(10L);
        technicalUnitSource = mock(TacticalUnit.class);
        technicalUnitTarget = mock(TacticalUnit.class);
        when(technicalUnitSource.getUser())
                .thenAnswer(x ->  mock(User.class));
        when(technicalUnitTarget.getUser())
                .thenAnswer(x ->  mock(User.class));
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