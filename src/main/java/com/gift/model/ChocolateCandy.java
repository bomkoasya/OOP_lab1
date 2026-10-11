package com.gift.model;

import com.gift.exception.InvalidSweetParameterException;

public class ChocolateCandy extends Sweet {
    private final double cocoaPercentage;
    private final boolean hasNuts;

    public ChocolateCandy(String name, double weight, double sugarContent, double price,
                          double cocoaPercentage, boolean hasNuts) {
        super(name, weight, sugarContent, price);
        if (cocoaPercentage < 0 || cocoaPercentage > 100) {
            throw new InvalidSweetParameterException("Відсоток какао має бути від 0 до 100%: " + cocoaPercentage);
        }
        this.cocoaPercentage = cocoaPercentage;
        this.hasNuts = hasNuts;
    }

    public double getCocoaPercentage() {
        return cocoaPercentage;
    }

    public boolean isHasNuts() {
        return hasNuts;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Шоколадна: какао %.1f%%, горіхи: %s)",
                cocoaPercentage, hasNuts ? "так" : "ні");
    }
}