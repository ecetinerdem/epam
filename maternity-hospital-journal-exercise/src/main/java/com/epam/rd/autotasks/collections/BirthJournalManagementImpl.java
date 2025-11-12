package com.epam.rd.autotasks.collections;

import java.util.*;

public class BirthJournalManagementImpl implements BirthJournalManagement {

    private Map<WeekDay, List<Baby>> journal;
    private boolean isCommitted;

    public BirthJournalManagementImpl() {
        this.journal = new HashMap<>();
        for (WeekDay day : WeekDay.values()) {
            journal.put(day, new ArrayList<>());
        }
        this.isCommitted = false;
    }

    @Override
    public boolean addEntryOfBaby(WeekDay day, Baby baby) {
        if (isCommitted) {
            return false;
        }
        if (baby == null || day == null) {
            return false;
        }
        if (baby.getName() == null || baby.getName().isEmpty()) {
            return false;
        }
        List<Baby> babies = journal.get(day);
        return babies.add(baby);
    }

    @Override
    public void commit() {
        if (!isCommitted) {
            Map<WeekDay, List<Baby>> unmodifiableJournal = new HashMap<>();
            for (Map.Entry<WeekDay, List<Baby>> entry : journal.entrySet()) {
                unmodifiableJournal.put(entry.getKey(),
                        Collections.unmodifiableList(new ArrayList<>(entry.getValue())));
            }
            journal = Collections.unmodifiableMap(unmodifiableJournal);
            isCommitted = true;
        }
    }

    @Override
    public int amountBabies() {
        int count = 0;
        for (List<Baby> babies : journal.values()) {
            count += babies.size();
        }
        return count;
    }

    @Override
    public List<Baby> findBabyWithHighestWeight(String gender) {
        List<Baby> result = new ArrayList<>();
        double maxWeight = Double.MIN_VALUE;

        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                if (baby.getGender().equals(gender)) {
                    if (baby.getWeight() > maxWeight) {
                        maxWeight = baby.getWeight();
                        result.clear();
                        result.add(baby);
                    } else if (baby.getWeight() == maxWeight) {
                        result.add(baby);
                    }
                }
            }
        }

        // Sort alphabetically by name
        Collections.sort(result, new Comparator<Baby>() {
            @Override
            public int compare(Baby b1, Baby b2) {
                return b1.getName().compareTo(b2.getName());
            }
        });

        return Collections.unmodifiableList(result);
    }

    @Override
    public List<Baby> findBabyWithSmallestHeight(String gender) {
        List<Baby> result = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;

        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                if (baby.getGender().equals(gender)) {
                    if (baby.getHeight() < minHeight) {
                        minHeight = baby.getHeight();
                        result.clear();
                        result.add(baby);
                    } else if (baby.getHeight() == minHeight) {
                        result.add(baby);
                    }
                }
            }
        }

        // Sort by weight in ascending order
        Collections.sort(result, new Comparator<Baby>() {
            @Override
            public int compare(Baby b1, Baby b2) {
                return Double.compare(b1.getWeight(), b2.getWeight());
            }
        });

        return Collections.unmodifiableList(result);
    }

    @Override
    public Set<Baby> findBabiesByBirthTime(String from, String to) {
        Set<Baby> result = new HashSet<>();

        int fromMinutes = parseTimeToMinutes(from);
        int toMinutes = parseTimeToMinutes(to);

        for (List<Baby> babies : journal.values()) {
            for (Baby baby : babies) {
                int birthTimeMinutes = parseTimeToMinutes(baby.getTime());
                if (birthTimeMinutes >= fromMinutes && birthTimeMinutes <= toMinutes) {
                    result.add(baby);
                }
            }
        }

        return result;
    }

    private int parseTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}