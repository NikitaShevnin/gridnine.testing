package com.gridnine.testing.filter;

import com.gridnine.testing.entity.FlightEntity;
import com.gridnine.testing.entity.SegmentEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тестовый класс для проверки функциональности FlightFilter.
 */
public class FlightFilterTest {

    /**
     * Проверка: метод filterFlights() возвращает отфильтрованные рейсы.
     */
    @Test
    public void filterFlights_ShouldReturnFilteredFlights() {
        FlightFilter filter = new FlightFilter();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime depInFuture = now.plusHours(2);
        LocalDateTime arrInFuture = depInFuture.plusHours(1);

        List<FlightEntity> flights = List.of(
                new FlightEntity(List.of(new SegmentEntity(depInFuture, arrInFuture))), // действующий рейс
                new FlightEntity(List.of(new SegmentEntity(now.minusHours(1), now))) // недействующий рейс
        );

        List<FlightEntity> filteredFlights = filter.filterFlights(flights);
        // Проверяем, что осталось только 1 рейс
        assertEquals(1, filteredFlights.size(), "Должен вернуть только 1 рейс");
    }

    /**
     * Проверка: метод isFlightAfterCurrentTime() возвращает true для будущего рейса.
     */
    @Test
    public void isFlightAfterCurrentTime_ShouldReturnTrue() {
        FlightFilter filter = new FlightFilter();
        LocalDateTime futureDate = LocalDateTime.now().plusHours(1);
        FlightEntity flight = new FlightEntity(List.of(new SegmentEntity(futureDate, futureDate.plusHours(1))));

        // Проверяем, что рейс действительно после текущего времени
        assertTrue(filter.isFlightAfterCurrentTime(flight, LocalDateTime.now()), "Рейс должен быть после текущего времени");
    }
}