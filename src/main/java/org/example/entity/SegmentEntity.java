package org.example.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Класс, представляющий сегмент рейса.
 */
public class SegmentEntity {
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;

    /**
     * Конструктор с параметрами объекта Segment.
     *
     * @param dep дата отправления
     * @param arr дата прибытия
     */
    public SegmentEntity(final LocalDateTime dep, final LocalDateTime arr) {
        departureDate = Objects.requireNonNull(dep);
        arrivalDate = Objects.requireNonNull(arr);
    }

    /**
     * Получает дату отправления сегмента.
     *
     * @return дата отправления
     */
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * Получает дату прибытия сегмента.
     *
     * @return дата прибытия
     */
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Возвращает строковое представление сегмента рейса в формате "[отправление|прибытие]".
     *
     * @return строка с датой и временем отправления и прибытия
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt) + ']';
    }
}