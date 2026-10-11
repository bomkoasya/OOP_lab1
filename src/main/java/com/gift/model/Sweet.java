package com.gift.model;

import com.gift.exception.InvalidSweetParameterException;
import java.util.Objects;

/**
 * Базовий клас для всіх солодощів у подарунку.
 * Реалізує інкапсуляцію спільних полів та базову валідацію.
 */
public abstract class Sweet implements Comparable<Sweet> {
    private final String name;
    private final double weight; // у грамах
    private final double sugarContent; // у відсотках (0 - 100)
    private final double price; // у гривнях

    protected Sweet(String name, double weight, double sugarContent, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidSweetParameterException("Назва солодощів не може бути порожньою.");
        }
        if (weight <= 0) {
            throw new InvalidSweetParameterException("Вага повинна бути більшою за нуль: " + weight);
        }
        if (sugarContent < 0 || sugarContent > 100) {
            throw new InvalidSweetParameterException("Вміст цукру має бути в межах від 0 до 100%: " + sugarContent);
        }
        if (price < 0) {
            throw new InvalidSweetParameterException("Ціна не може бути від'ємною: " + price);
        }

        this.name = name.trim();
        this.weight = weight;
        this.sugarContent = sugarContent;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getSugarContent() {
        return sugarContent;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Природне впорядкування за назвою за замовчуванням.
     */
    @Override
    public int compareTo(Sweet other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sweet sweet = (Sweet) o;
        return Double.compare(sweet.weight, weight) == 0 &&
               Double.compare(sweet.sugarContent, sugarContent) == 0 &&
               Double.compare(sweet.price, price) == 0 &&
               Objects.equals(name, sweet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, sugarContent, price);
    }

    @Override
    public String toString() {
        return String.format("%s [вага: %.1f г, цукор: %.1f%%, ціна: %.2f грн]",
                name, weight, sugarContent, price);
    }
}