package com.gift.model;

import com.gift.exception.InvalidSweetParameterException;

public class Waffle extends Sweet {
    private final String fillingType;
    private final int layerCount;

    public Waffle(String name, double weight, double sugarContent, double price,
                  String fillingType, int layerCount) {
        super(name, weight, sugarContent, price);
        if (fillingType == null || fillingType.trim().isEmpty()) {
            throw new InvalidSweetParameterException("Тип начинки вафель має бути вказаний.");
        }
        if (layerCount <= 0) {
            throw new InvalidSweetParameterException("Кількість шарів має бути більшою за 0: " + layerCount);
        }
        this.fillingType = fillingType.trim();
        this.layerCount = layerCount;
    }

    public String getFillingType() {
        return fillingType;
    }

    public int getLayerCount() {
        return layerCount;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Вафля: начинка '%s', шарів: %d)",
                fillingType, layerCount);
    }
}