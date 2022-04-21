package ru.vkn.appowar;

import java.util.List;

import lombok.Value;

@Value
public class TacticalUnitImplementation implements TacticalUnit {
    User user;
    List<UnitImplementation> units;
    double firePower;
}
