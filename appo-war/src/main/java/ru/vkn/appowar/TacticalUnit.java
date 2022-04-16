package ru.vkn.appowar;

import java.util.List;

public interface TacticalUnit {
    User getUser();

    List<Unit> getUnits();

    double getFirePower();
}
