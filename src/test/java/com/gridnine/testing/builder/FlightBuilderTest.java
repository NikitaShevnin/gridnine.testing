package com.gridnine.testing.builder;

import com.gridnine.testing.entity.FlightEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тестовый класс для проверки функциональности FlightBuilder.
 */
public class FlightBuilderTest {

    /**
     * Проверка: метод createFlights() возвращает непустой список рейсов.
     */
    @Test
    public void createFlights_ShouldReturnNonEmptyList() {
        List<FlightEntity> flights = FlightBuilder.createFlights();
        // Список не должен быть пустым
        assertFalse(flights.isEmpty(), "Список рейсов не должен быть пустым");
    }

    /**
     * Проверка: метод createFlight() выбрасывает исключение при нечётном количестве дат.
     */
    @Test
    public void createFlight_ShouldThrowExceptionForOddNumberOfDates() {
        LocalDateTime now = LocalDateTime.now();
        // Ожидаем IllegalArgumentException при передаче текущего времени
        assertThrows(IllegalArgumentException.class, () -> FlightBuilder.createFlight(now));
    }

    /**
     * Проверка: метод createFlight() создаёт рейс с двумя сегментами.
     */
    @Test
    public void createFlight_ShouldCreateFlightWithSegments() {
        LocalDateTime dep1 = LocalDateTime.now();
        LocalDateTime arr1 = dep1.plusHours(1);
        LocalDateTime dep2 = arr1.plusHours(1);
        LocalDateTime arr2 = dep2.plusHours(1);

        FlightEntity flight = FlightBuilder.createFlight(dep1, arr1, dep2, arr2);
        // Проверяем, что было создано 2 сегмента
        assertEquals(2, flight.getSegments().size(), "Должно быть 2 сегмента");
    }
}