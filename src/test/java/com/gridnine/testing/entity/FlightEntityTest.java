package com.gridnine.testing.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестовый класс для проверки функциональности FlightEntity.
 */
public class FlightEntityTest {

    /**
     * Проверка: метод getSegments() возвращает все сегменты рейса.
     */
    @Test
    public void getSegments_ShouldReturnAllSegments() {
        LocalDateTime dep1 = LocalDateTime.now();
        LocalDateTime arr1 = dep1.plusHours(1);
        SegmentEntity segment = new SegmentEntity(dep1, arr1);
        FlightEntity flight = new FlightEntity(List.of(segment));

        // Проверяем, что возвращается один сегмент
        assertEquals(1, flight.getSegments().size(), "Должен вернуть один сегмент");
    }

    /**
     * Проверка: метод toString() возвращает отформатированную строку.
     */
    @Test
    public void toString_ShouldReturnFormattedString() {
        LocalDateTime dep1 = LocalDateTime.now();
        LocalDateTime arr1 = dep1.plusHours(1);
        SegmentEntity segment = new SegmentEntity(dep1, arr1);
        FlightEntity flight = new FlightEntity(List.of(segment));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        // Ожидаемое строковое представление
        String expected = "[" + dep1.format(formatter) + "|" + arr1.format(formatter) + "]";
        // Проверяем, что строковое представление совпадает с ожидаемым
        assertEquals(expected, flight.toString(), "Строковое представление рейса должно совпадать с ожидаемым");
    }
}