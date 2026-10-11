package com.gift.service;

import com.gift.exception.InvalidSweetParameterException;
import com.gift.model.Sweet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Клас, що представляє дитячий подарунок.
 * Відповідає за агрегацію солодощів, обчислення ваги, сортування та пошук.
 */
public class Gift {
    private final List<Sweet> sweets;

    public Gift() {
        this.sweets = new ArrayList<>();
    }

    public Gift(List<Sweet> sweets) {
        if (sweets == null) {
            this.sweets = new ArrayList<>();
        } else {
            this.sweets = new ArrayList<>(sweets);
        }
    }

    public void addSweet(Sweet sweet) {
        Objects.requireNonNull(sweet, "Неможливо додати null-об'єкт до подарунка.");
        sweets.add(sweet);
    }

    public List<Sweet> getSweets() {
        return Collections.unmodifiableList(sweets);
    }

    /**
     * Обчислює загальну вагу подарунка в грамах.
     */
    public double calculateTotalWeight() {
        return sweets.stream()
                .mapToDouble(Sweet::getWeight)
                .sum();
    }

    /**
     * Сортує солодощі в подарунку за заданим компаратором.
     */
    public void sort(Comparator<Sweet> comparator) {
        Objects.requireNonNull(comparator, "Компаратор не може бути null.");
        sweets.sort(comparator);
    }

    /**
     * Зручний метод для сортування за вагою (за зростанням).
     */
    public void sortByWeight() {
        sort(Comparator.comparingDouble(Sweet::getWeight));
    }

    /**
     * Зручний метод для сортування за вмістом цукру (за зростанням).
     */
    public void sortBySugarContent() {
        sort(Comparator.comparingDouble(Sweet::getSugarContent));
    }

    /**
     * Знаходить усі солодощі, вміст цукру яких потрапляє в діапазон [minSugar, maxSugar].
     */
    public List<Sweet> findSweetsBySugarRange(double minSugar, double maxSugar) {
        if (minSugar > maxSugar) {
            throw new InvalidSweetParameterException(
                    String.format("Початковий діапазон (%.2f) не може перевищувати кінцевий (%.2f)", minSugar, maxSugar));
        }
        return sweets.stream()
                .filter(s -> s.getSugarContent() >= minSugar && s.getSugarContent() <= maxSugar)
                .collect(Collectors.toList());
    }
}