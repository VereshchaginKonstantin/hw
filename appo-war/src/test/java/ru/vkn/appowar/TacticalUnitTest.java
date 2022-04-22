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
    void testRealPower2() {
        // TODO: 2. защита
        var unit = new UnitImplementation(100, 7, 100, 15, 2);
        assertThat(unit.getRealPower()).isEqualTo(18);
    }

}