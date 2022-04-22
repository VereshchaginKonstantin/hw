package ru.vkn.appowar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TacticalUnitTest {

    @Test
    void testRealPower1() {
        var unit = new UnitImplementation(100, 7, 100, 15, 1);
        assertThat(unit.getRealPower()).isEqualTo(15);
    }

    @Test
    void testRealDefence1() {
        var unit = new UnitImplementation(100, 10, 100, 15, 1);
        assertThat(unit.getRealDefence()).isEqualTo(10);
    }

    @Test
    void testRealDefence2() {
        var unit = new UnitImplementation(100, 10, 50, 15, 2);
        assertThat(unit.getRealDefence()).isEqualTo(6);
    }

    @Test
    void testRealDefence3() {
        var unit = new UnitImplementation(100, 10, 50, 15, 0);
        assertThat(unit.getRealDefence()).isEqualTo(3);
    }

    @Test
    void testRealPower2() {
        var unit = new UnitImplementation(100, 7, 100, 15, 2);
        assertThat(unit.getRealPower()).isEqualTo(18);
    }

}