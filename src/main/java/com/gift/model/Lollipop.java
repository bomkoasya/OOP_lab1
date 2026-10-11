package com.gift.model;

import com.gift.exception.InvalidSweetParameterException;

public class Lollipop extends Sweet {
    private final String flavor;
    private final boolean hasGumCenter;

    public Lollipop(String name, double weight, double sugarContent, double price,
                    String flavor, boolean hasGumCenter) {
        super(name, weight, sugarContent, price);
        if (flavor == null || flavor.trim().isEmpty()) {
            throw new InvalidSweetParameterException("Смак льодяника має бути вказаний.");
        }
        this.flavor = flavor.trim();
        this.hasGumCenter = hasGumCenter;
    }

    public String getFlavor() {
        return flavor;
    }

    public boolean isHasGumCenter() {
        return hasGumCenter;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Льодяник: смак '%s', жуйка: %s)",
                flavor, hasGumCenter ? "так" : "ні");
    }
}