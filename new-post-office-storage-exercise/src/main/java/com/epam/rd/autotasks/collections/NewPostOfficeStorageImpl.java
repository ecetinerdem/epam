package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class NewPostOfficeStorageImpl implements NewPostOfficeStorage {
    private List<Box> parcels;

    public NewPostOfficeStorageImpl() {
        parcels = new LinkedList<>();
    }

    public NewPostOfficeStorageImpl(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Collection cannot be null");
        }

        Iterator<Box> itCheck = boxes.iterator();
        while (itCheck.hasNext()) {
            Box b = itCheck.next();
            if (b == null) {
                throw new NullPointerException("Collection contains null values");
            }
        }

        parcels = new LinkedList<>();
        Iterator<Box> itAdd = boxes.iterator();
        while (itAdd.hasNext()) {
            Box b = itAdd.next();
            parcels.add(b);
        }
    }

    @Override
    public boolean acceptBox(Box box) {
        if (box == null) {
            throw new NullPointerException("Box cannot be null");
        }
        return parcels.add(box);
    }

    @Override
    public boolean acceptAllBoxes(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Collection cannot be null");
        }

        Iterator<Box> check = boxes.iterator();
        while (check.hasNext()) {
            if (check.next() == null) {
                throw new NullPointerException("Collection contains null values");
            }
        }

        boolean result = false;
        Iterator<Box> it = boxes.iterator();
        while (it.hasNext()) {
            Box b = it.next();
            if (parcels.add(b)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean carryOutBoxes(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("Collection cannot be null");
        }

        Iterator<Box> check = boxes.iterator();
        while (check.hasNext()) {
            if (check.next() == null) {
                throw new NullPointerException("Collection contains null values");
            }
        }

        boolean result = false;
        Iterator<Box> iterator = parcels.iterator();

        while (iterator.hasNext()) {
            Box currentBox = iterator.next();

            Iterator<Box> itRemove = boxes.iterator();
            while (itRemove.hasNext()) {
                Box removeCandidate = itRemove.next();
                if (currentBox.equals(removeCandidate)) {
                    iterator.remove();
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public List<Box> carryOutBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }

        List<Box> removedBoxes = new ArrayList<>();
        Iterator<Box> iterator = parcels.iterator();

        while (iterator.hasNext()) {
            Box current = iterator.next();
            if (predicate.test(current)) {
                removedBoxes.add(current);
                iterator.remove();
            }
        }
        return removedBoxes;
    }

    @Override
    public List<Box> getAllWeightLessThan(final double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }

        Predicate<Box> predicate = new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getWeight() < weight;
            }
        };
        return searchBoxes(predicate);
    }

    @Override
    public List<Box> getAllCostGreaterThan(final BigDecimal cost) {
        if (cost == null) {
            throw new NullPointerException("Cost cannot be null");
        }
        if (cost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

        Predicate<Box> predicate = new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getCost().compareTo(cost) > 0;
            }
        };
        return searchBoxes(predicate);
    }

    @Override
    public List<Box> getAllVolumeGreaterOrEqual(final double volume) {
        if (volume <= 0) {
            throw new IllegalArgumentException("Volume cannot be negative");
        }

        Predicate<Box> predicate = new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getVolume() >= volume;
            }
        };
        return searchBoxes(predicate);
    }

    @Override
    public List<Box> searchBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }

        List<Box> result = new ArrayList<>();
        Iterator<Box> iterator = parcels.iterator();

        while (iterator.hasNext()) {
            Box current = iterator.next();
            if (predicate.test(current)) {
                result.add(current);
            }
        }
        return result;
    }

    @Override
    public void updateOfficeNumber(Predicate<Box> predicate, int newOfficeNumber) {
        if (predicate == null) {
            throw new NullPointerException("Predicate cannot be null");
        }
        if (newOfficeNumber <= 0) {
            throw new IllegalArgumentException("Office number must be positive");
        }

        Iterator<Box> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            Box current = iterator.next();
            if (predicate.test(current)) {
                current.setOfficeNumber(newOfficeNumber);
            }
        }
    }
}
