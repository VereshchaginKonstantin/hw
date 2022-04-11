package ru.otus.tests;

import org.junit.jupiter.api.Test;
import ru.otus.FightEngine;

class FightEngineTest {

    @Test
    void fight() throws Exception {
        var eng = new FightEngine();
        eng.fight();
    }
}