package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * "Сегмент" (Segment) представляет собой сущность части рейса, которая
 * включает в себя информацию о времени отправления и прибытия.
 * Класс, представляющий сегмент рейса.
 */
class SegmentEntity {
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;

    /**
     * Конструктор с параметрами объекта Segment.
     *
     * @param dep дата отправления
     * @param arr дата прибытия
     */
    SegmentEntity(final LocalDateTime dep, final LocalDateTime arr) {
        departureDate = Objects.requireNonNull(dep);
        arrivalDate = Objects.requireNonNull(arr);
    }

    /**
     * Геттер на дату отправления сегмента.
     *
     * @return дата отправления
     */
    LocalDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * Геттер на дату прибытия сегмента.
     *
     * @return дата прибытия
     */
    LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Возвращает строковое представление сегмента рейса в формате "[отправление|прибытие]".
     *
     * @return строка, содержащая дату и время отправления и прибытия сегмента,
     *         отформатированные по шаблону "yyyy-MM-dd'T'HH:mm".
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt) + ']';
    }
}