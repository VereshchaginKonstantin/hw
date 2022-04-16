package ru.vkn.appowar;

import java.util.HashMap;
import java.util.Map;

import javax.management.OperationsException;

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
        this.userSecond = userSecond;
        this.distance = distance;
    }

    @Override
    public long getDistance(TacticalUnit technicalUnitSource, TacticalUnit technicalUnitTarget) {
        return Math.abs(positions.get(technicalUnitTarget) -
                positions.get(technicalUnitSource));
    }

    @Override
    public void moveForward(TacticalUnit technicalUnit) throws OperationsException {
        move(technicalUnit, 1);
    }

    @Override
    public void moveBackward(TacticalUnit technicalUnit) throws OperationsException {
        move(technicalUnit, -1);
    }

    private void move(TacticalUnit technicalUnit, int l) throws OperationsException {
        var user = technicalUnit.getUser();
        if (userFirst.equals(user)) {
            positions.put(technicalUnit,
                    positions.get(technicalUnit) + l);
        } else if (userSecond.equals(user)) {
            positions.put(technicalUnit,
                    positions.get(technicalUnit) - l);
        } else {
            throw new OperationsException("user not found");
        }
    }

    @Override
    public void add(TacticalUnit technicalUnit) throws OperationsException {
        var user = technicalUnit.getUser();
        if (userFirst.equals(user)) {
            positions.put(technicalUnit, 0L);
        } else if (userSecond.equals(user)) {
            positions.put(technicalUnit, distance);
        } else {
            throw new OperationsException("user not found");
        }
        units.put(user, technicalUnit);
    }
}
