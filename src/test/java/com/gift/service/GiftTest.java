package com.gift.service;

import com.gift.exception.InvalidSweetParameterException;
import com.gift.model.ChocolateCandy;
import com.gift.model.Lollipop;
import com.gift.model.Sweet;
import com.gift.model.Waffle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiftTest {

    private Gift gift;

    @Mock
    private Sweet mockedSweet1;

    @Mock
    private Sweet mockedSweet2;

    @BeforeEach
    void setUp() {
        gift = new Gift();
    }

    @Test
    @DisplayName("Перевірка розрахунку загальної ваги за допомогою Mockito")
    void testCalculateTotalWeightWithMocks() {
        when(mockedSweet1.getWeight()).thenReturn(25.5);
        when(mockedSweet2.getWeight()).thenReturn(74.5);

        gift.addSweet(mockedSweet1);
        gift.addSweet(mockedSweet2);

        assertEquals(100.0, gift.calculateTotalWeight(), 0.001);
    }

    @Test
    @DisplayName("Перевірка сортування за вагою")
    void testSortByWeight() {
        Sweet light = new Lollipop("Малий", 10.0, 80.0, 3.0, "Вишня", false);
        Sweet medium = new ChocolateCandy("Середній", 20.0, 50.0, 10.0, 60.0, true);
        Sweet heavy = new Waffle("Важкий", 50.0, 30.0, 12.0, "Ваніль", 3);

        gift.addSweet(heavy);
        gift.addSweet(light);
        gift.addSweet(medium);

        gift.sortByWeight();
        List<Sweet> result = gift.getSweets();

        assertEquals(3, result.size());
        assertEquals("Малий", result.get(0).getName());
        assertEquals("Середній", result.get(1).getName());
        assertEquals("Важкий", result.get(2).getName());
    }

    @Test
    @DisplayName("Перевірка пошуку цукерок за діапазоном цукру")
    void testFindSweetsBySugarRange() {
        Sweet lowSugar = new Waffle("Низький цукор", 30.0, 25.0, 10.0, "Лимон", 2);
        Sweet target1 = new ChocolateCandy("Цільова 1", 15.0, 45.0, 8.0, 55.0, false);
        Sweet target2 = new ChocolateCandy("Цільова 2", 18.0, 55.0, 9.0, 50.0, true);
        Sweet highSugar = new Lollipop("Високий цукор", 12.0, 85.0, 4.0, "Карамель", true);

        gift.addSweet(lowSugar);
        gift.addSweet(target1);
        gift.addSweet(target2);
        gift.addSweet(highSugar);

        List<Sweet> filtered = gift.findSweetsBySugarRange(40.0, 60.0);

        assertEquals(2, filtered.size());
        assertTrue(filtered.contains(target1));
        assertTrue(filtered.contains(target2));
    }

    @Test
    @DisplayName("Помилка при некоректному діапазоні цукру (min > max)")
    void testInvalidSugarRangeThrowsException() {
        assertThrows(InvalidSweetParameterException.class, () ->
                gift.findSweetsBySugarRange(70.0, 30.0)
        );
    }

    @Test
    @DisplayName("Валідація вхідних даних при створенні сутностей")
    void testSweetValidation() {
        assertThrows(InvalidSweetParameterException.class, () ->
                new ChocolateCandy("", 10.0, 50.0, 5.0, 50.0, false)
        );
        assertThrows(InvalidSweetParameterException.class, () ->
                new ChocolateCandy("Конфета", -5.0, 50.0, 5.0, 50.0, false)
        );
        assertThrows(InvalidSweetParameterException.class, () ->
                new ChocolateCandy("Конфета", 10.0, 110.0, 5.0, 50.0, false)
        );
    }
}