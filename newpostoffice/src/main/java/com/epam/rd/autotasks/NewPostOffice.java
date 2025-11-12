package com.epam.rd.autotasks;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NewPostOffice {
    private final Collection<Box> listBox;
    private static final int COST_KILOGRAM = 5;
    private static final int COST_CUBIC_METER = 100;
    private static final double COEFFICIENT = 0.5;

    public NewPostOffice() {
        listBox = new ArrayList<>();
    }

    public Collection<Box> getListBox() {
        return (Collection<Box>) ((ArrayList<Box>) listBox).clone();
    }

    static BigDecimal calculateCostOfBox(double weight, double volume, int value) {
        BigDecimal costWeight = BigDecimal.valueOf(weight)
                .multiply(BigDecimal.valueOf(COST_KILOGRAM), MathContext.DECIMAL64);
        BigDecimal costVolume = BigDecimal.valueOf(volume)
                .multiply(BigDecimal.valueOf(COST_CUBIC_METER), MathContext.DECIMAL64);
        return costVolume.add(costWeight)
                .add(BigDecimal.valueOf(COEFFICIENT * value), MathContext.DECIMAL64);
    }

    // implements student
    public boolean addBox(String addresser, String recipient, double weight, double volume, int value) {
        if (addresser == null || addresser.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be null or empty");
        }

        if (recipient == null || recipient.trim().isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be null or empty");
        }

        if (weight < 0.5 || weight > 20.0) {
            throw new IllegalArgumentException("Weight must be between 0.5 and 20.0 kg");
        }

        if (volume <= 0 || volume > 0.25) {  // ✅ Change >= to >
            throw new IllegalArgumentException("Volume must be greater than 0 and less than or equal to 0.25 m³");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("Value must be greater than zero");
        }

        Box box = new Box(addresser, recipient, weight, volume);

        BigDecimal cost = calculateCostOfBox(weight, volume, value);
        box.setCost(cost);

        listBox.add(box);
        return true;
    }

    // implements student
    public Collection<Box> deliveryBoxToRecipient(String recipient) {
        List<Box> deliveredBoxes = new ArrayList<>();
        Iterator<Box> iterator = listBox.iterator();

        while (iterator.hasNext()) {
            Box currentBox = iterator.next();
            if (currentBox.getRecipient().equalsIgnoreCase(recipient)) {
                deliveredBoxes.add(currentBox);
                iterator.remove();
            }
        }
        return deliveredBoxes;
    }

    public void declineCostOfBox(double percent) {
        Iterator<Box> iterator = listBox.iterator();
        while (iterator.hasNext()) {
            Box currentBox = iterator.next();
            BigDecimal currentCost = currentBox.getCost();

            BigDecimal discountMultiplier = BigDecimal.valueOf(100 - percent)
                    .divide(BigDecimal.valueOf(100), MathContext.DECIMAL64);
            BigDecimal newCost = currentCost.multiply(discountMultiplier, MathContext.DECIMAL64);
            currentBox.setCost(newCost);

        }
    }

}
