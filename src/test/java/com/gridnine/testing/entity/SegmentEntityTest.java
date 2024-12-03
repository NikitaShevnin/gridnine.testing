package com.gridnine.testing.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Тестовый класс для проверки функциональности SegmentEntity.
 */
public class SegmentEntityTest {

    /**
     * Проверка: конструктор с корректными параметрами создаёт экземпляр SegmentEntity.
     */
    @Test
    public void constructor_WithValidParameters_ShouldCreateSegmentEntity() {
        LocalDateTime departure = LocalDateTime.now();
        LocalDateTime arrival = departure.plusHours(2);

        SegmentEntity segment = new SegmentEntity(departure, arrival);

        // Проверяем, что сегмент не null
        assertNotNull(segment, "Сегмент не должен быть null");
        // Проверяем, что даты совпадают с заданными
        assertEquals(departure, segment.getDepartureDate(),
                "Дата отправления должна совпадать с заданной");
        assertEquals(arrival, segment.getArrivalDate(),
                "Дата прибытия должна совпадать с заданной");
    }

    /**
     * Проверка: метод toString() возвращает отформатированную строку.
     */
    @Test
    public void toString_ShouldReturnFormattedString() {
        LocalDateTime departure = LocalDateTime.of(
                2023, 10, 1, 12, 0);
        LocalDateTime arrival = departure.plusHours(2);

        SegmentEntity segment = new SegmentEntity(departure, arrival);

        String expected = "[2023-10-01T12:00|2023-10-01T14:00]";
        // Проверяем, что строковое представление сегмента совпадает с ожидаемым
        assertEquals(expected, segment.toString(),
                "Строковое представление сегмента должно совпадать с ожидаемым");
    }

    /**
     * Проверка: конструктор по умолчанию инициализирует с текущим временем.
     */
    @Test
    public void defaultConstructor_ShouldInitializeWithCurrentTime() {
        SegmentEntity segment = new SegmentEntity();

        // Проверяем, что сегмент инициализирован
        assertNotNull(segment, "Сегмент не должен быть null");
        assertNotNull(segment.getDepartureDate(), "Дата отправления не должна быть null");
        assertNotNull(segment.getArrivalDate(), "Дата прибытия не должна быть null");
        // Проверяем, что дата прибытия позже даты отправления
        assertTrue(segment.getArrivalDate().isAfter(segment.getDepartureDate()),
                "Дата прибытия должна быть позже даты отправления");
    }
}