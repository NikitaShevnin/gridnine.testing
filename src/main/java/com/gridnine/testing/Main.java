package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.entity.FlightEntity;
import com.gridnine.testing.entity.SegmentEntity;
import com.gridnine.testing.filter.FlightFilter;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Получаем тестовый набор авиаперелетов
        List<FlightEntity> flights = FlightBuilder.createFlights();

        // Создаем экземпляр фильтра
        FlightFilter filter = new FlightFilter();

        // Фильтрация по всем правилам
        System.out.println("Все рейсы:");
        flights.forEach(System.out::println);

        // Фильтрация по времени вылета
        List<FlightEntity> afterCurrentTimeFlights = filter.filterFlights(flights);
        System.out.println("\nРейсы, вылетающие после текущего времени:");
        afterCurrentTimeFlights.forEach(System.out::println);

        // Фильтрация по корректности дат сегментов
        List<FlightEntity> validSegmentsFlights = afterCurrentTimeFlights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate()
                                .isAfter(segment.getDepartureDate()))
                ).toList();
        System.out.println("\nРейсы с корректными датами сегментов:");
        validSegmentsFlights.forEach(System.out::println);

        // Фильтрация по времени на земле
        List<FlightEntity> validGroundTimeFlights = validSegmentsFlights.stream()
                .filter(flight -> {
                    Duration totalGroundTime = Duration.ZERO;
                    List<SegmentEntity> segments = flight.getSegments();

                    for (int i = 0; i < segments.size() - 1; i++) {
                        SegmentEntity currentSegment = segments.get(i);
                        SegmentEntity nextSegment = segments.get(i + 1);
                        Duration groundTime = Duration.between(currentSegment
                                .getArrivalDate(), nextSegment.getDepartureDate());
                        totalGroundTime = totalGroundTime.plus(groundTime);
                    }

                    return totalGroundTime.toHours() <= 2;
                }).toList();
        System.out.println("\nРейсы с общим временем на земле не более 2 часов:");
        validGroundTimeFlights.forEach(System.out::println);
    }
}