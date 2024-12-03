package com.gridnine.testing.builder;

import com.gridnine.testing.entity.FlightEntity;
import com.gridnine.testing.entity.SegmentEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Фабричный класс для создания тестовых рейсов.
 */
public class FlightBuilder {

    /**
     * Создает список тестовых рейсов.
     *
     * @return список тестовых рейсов
     */
    public static List<FlightEntity> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        return Arrays.asList(
                // Создание рейса, который начинается через 3 дня и длится 2 часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                // Создание рейса с несколькими промежуточными остановками
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                // Создание рейса, который стартует 6 дней назад и заканчивается через 3 дня
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),

                // Создание рейса, у которого время начала позже времени окончания (недопустимая ситуация)
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),

                // Создание рейса с несколькими промежуточными остановками
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),

                // Создание рейса с ещё большим количеством промежуточных остановок
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))
        );
    }

    /**
     * Создает рейс с заданными датами отправления и прибытия.
     *
     * @param dates массив дат отправления и прибытия
     * @return объект FlightEntity
     * @throws IllegalArgumentException если количество дат нечетное
     */
    public static FlightEntity createFlight(final LocalDateTime... dates) {
        // Проверка на четное количество переданных дат
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException("Вы должны передать четное количество дат");
        }

        // Инициализация списка сегментов делим на 2 для того, чтобы не занимать много памяти
        // используем ровно столько памяти, сколько нужно для работы
        List<SegmentEntity> segmentEntities = new ArrayList<>(dates.length / 2);

        // Обработка пар дат для создания сегментов
        for (int i = 0; i < (dates.length - 1); i += 2) {
            // Создаем новый сегмент с датами отправления и прибытия
            segmentEntities.add(new SegmentEntity(dates[i], dates[i + 1]));
        }

        // Возвращение нового объекта FlightEntity с созданными сегментами
        return new FlightEntity(segmentEntities);
    }
}