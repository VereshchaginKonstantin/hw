package ru.vkn.appowar;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class PosititonLinear implements Posititon {

    private Multimap<User, TacticalUnit> units = ArrayListMultimap.create();

    private Map<TacticalUnit, Long> positions = new HashMap<>();

    private User userFirst;

    private User userSecond;

    private long distance;

    public PosititonLinear(long distance,
                           User userFirst,
                           User userSecond) {
        this.userFirst = userFirst;
        this.userSecond = userFirst;
        this.distance = distance;
    }

    @Override
    public long getDistance(TacticalUnit technicalUnitSource, TacticalUnit technicalUnitTarget) {
        return positions.get(technicalUnitTarget) -
                positions.get(technicalUnitSource);
    }

    @Override
    public void moveForward(TacticalUnit technicalUnitSource) {
    }

    @Override
    public void add(TacticalUnit technicalUnit) {
        var user = technicalUnit.getUser();
        if (userSecond.equals(user)) {
            positions.put(technicalUnit, 0L);
        } else {
            positions.put(technicalUnit, distance);
        }
        units.put(user, technicalUnit);
    }
}
