package org.example.filter;

import org.example.entity.FlightEntity;
import org.example.entity.SegmentEntity;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для фильтрации рейсов по заданным правилам.
 * TODO: - Реализовать три правила фильтрации:
 *      1. Исключить рейсы с временем вылета до текущего момента.
 *      2. Исключить рейсы, в которых есть сегменты с датой прилета раньше даты вылета.
 *      3. Исключить рейсы, где общее время на земле превышает два часа (наземное время — это
 *        интервалы между прилетом одного сегмента и вылетом следующего за ним).
 */
public class FlightFilter {

    /**
     * Фильтрует список рейсов в соответствии с заданными правилами.
     *
     * @param flights список рейсов для фильтрации
     * @return отфильтрованный список рейсов
     */
    public List<FlightEntity> filterFlights(List<FlightEntity> flights) {
        // Получаем текущее время для фильтрации
        LocalDateTime now = LocalDateTime.now();


        return flights.stream()
                // Фильтруем рейсы, чтобы оставить только те, что вылетают после текущего времени
                .filter(flight -> isFlightAfterCurrentTime(flight, now))
                // Проверяем, что все сегменты рейса имеют корректные даты
                .filter(this::areSegmentsValid)
                // Проверяем, что общее время на земле не превышает 2 часов
                .filter(this::totalGroundTimeIsValid)
                // Собираем отфильтрованные рейсы в список
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, что рейс начинается после текущего момента.
     *
     * @param flight рейс для проверки
     * @param now текущее время
     * @return true, если рейс начинается после текущего момента, иначе false
     */
    private boolean isFlightAfterCurrentTime(FlightEntity flight, LocalDateTime now) {
        // Проверяем, что время отправления первого сегмента больше текущего времени
        return flight.getSegments().getFirst().getDepartureDate().isAfter(now);
    }

    /**
     * Проверяет, что все сегменты имеют корректные даты.
     *
     * @param flight рейс для проверки
     * @return true, если все сегменты корректны, иначе false
     */
    private boolean areSegmentsValid(FlightEntity flight) {
        // Проверяем, что для каждого сегмента время прибытия больше времени отправления
        return flight.getSegments().stream()
                .allMatch(segment -> segment.getArrivalDate()
                        .isAfter(segment.getDepartureDate()));
    }

    /**
     * Проверяет, что общее время на земле не превышает 2 часов.
     *
     * @param flight рейс для проверки
     * @return true, если время на земле в пределах 2 часов, иначе false
     */
    private boolean totalGroundTimeIsValid(FlightEntity flight) {
        // Инициализация переменной для хранения общего времени на земле
        Duration totalGroundTime = Duration.ZERO;
        List<SegmentEntity> segments = flight.getSegments();

        // Проход по всем сегментам, кроме последнего
        for (int i = 0; i < segments.size() - 1; i++) {
            SegmentEntity currentSegment = segments.get(i); // Текущий сегмент
            SegmentEntity nextSegment = segments.get(i + 1); // Следующий сегмент

            // Вычисляем время на земле между текущим сегментом и следующим
            Duration groundTime = Duration.between(currentSegment.getArrivalDate(),
                    nextSegment.getDepartureDate());
            totalGroundTime = totalGroundTime.plus(groundTime); // Суммируем время на земле
        }

        // Проверяем, что общее время на земле не превышает 2 часов
        return totalGroundTime.toHours() <= 2;
    }
}