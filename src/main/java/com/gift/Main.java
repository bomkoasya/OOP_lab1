package com.gift;

import com.gift.model.ChocolateCandy;
import com.gift.model.Lollipop;
import com.gift.model.Sweet;
import com.gift.model.Waffle;
import com.gift.service.Gift;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Формування новорічного подарунка ===");

        Gift gift = new Gift();
        gift.addSweet(new ChocolateCandy("Червоний мак", 15.5, 52.0, 8.50, 48.0, true));
        gift.addSweet(new ChocolateCandy("Трюфель", 18.0, 45.0, 12.00, 72.0, false));
        gift.addSweet(new Lollipop("Чупа-чупс Полуниця", 12.0, 85.0, 5.00, "Полуниця", true));
        gift.addSweet(new Lollipop("М'ятний льодяник", 10.0, 78.0, 4.00, "М'ята", false));
        gift.addSweet(new Waffle("Артек", 75.0, 38.0, 15.00, "Шоколадно-горіхова", 5));

        System.out.println("\nСклад подарунка:");
        gift.getSweets().forEach(sweet -> System.out.println(" - " + sweet));

        System.out.printf("\nЗагальна вага подарунка: %.1f г\n", gift.calculateTotalWeight());

        System.out.println("\n--- Сортування солодощів за вагою (зростання) ---");
        gift.sortByWeight();
        gift.getSweets().forEach(sweet -> System.out.println(" - " + sweet));

        double minSugar = 40.0;
        double maxSugar = 60.0;
        System.out.printf("\n--- Пошук солодощів із вмістом цукру від %.1f%% до %.1f%% ---\n", minSugar, maxSugar);
        List<Sweet> filteredSweets = gift.findSweetsBySugarRange(minSugar, maxSugar);

        if (filteredSweets.isEmpty()) {
            System.out.println("Солодощів у вказаному діапазоні не знайдено.");
        } else {
            filteredSweets.forEach(sweet -> System.out.println(" Знайдено: " + sweet));
        }
    }
}