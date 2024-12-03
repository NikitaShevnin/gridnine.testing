package com.gridnine.testing.entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, представляющий сущность рейса.
 * Содержит:
 * Конструктор FlightEntity
 * геттер getSegments
 * и метод toString для отображения информации из сегментов в консоли.
 */
public class FlightEntity {
    private final List<SegmentEntity> segmentEntities;

    /**
     * Конструктор объекта Flight.
     *
     * @param segs список сегментов рейса
     */
    public FlightEntity(final List<SegmentEntity> segs) {
        segmentEntities = segs;
    }

    /**
     * Получает список сегментов рейса.
     *
     * @return список сегментов
     */
    public List<SegmentEntity> getSegments() {
        return segmentEntities;
    }

    @Override
    public String toString() {
        return segmentEntities.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}