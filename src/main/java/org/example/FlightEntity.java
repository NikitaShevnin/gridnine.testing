package org.example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, представляющий рейс.
 */
class FlightEntity {

    private final List<SegmentEntity> segmentEntities;

    /**
     * Конструктор объекта Flight.
     *
     * @param segs список сегментов рейса
     */
    FlightEntity(final List<SegmentEntity> segs) {
        segmentEntities = segs;
    }

    /**
     * Получает список сегментов рейса.
     *
     * @return список сегментов
     */
    List<SegmentEntity> getSegments() {
        return segmentEntities;
    }

    @Override
    public String toString() {
        return segmentEntities.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}