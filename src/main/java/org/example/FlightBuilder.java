package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Фабричный класс для создания тестовых рейсов.
 */
class FlightBuilder {

    /**
     * Статический метод для создания списка рейсов
     *
     * @return список тестовых рейсов
     */
    static List<FlightEntity> createFlights() {

        // Определяем дату и время, которое соответствует текущему моменту плюс 3 дня
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        /*
         Возвращаем список из нескольких объектов типа Flight,
         созданных с помощью метода createFlight с разными параметрами
         */
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
     * @return объект Flight
     * @throws IllegalArgumentException если количество дат нечетное
     */
    private static FlightEntity createFlight(final LocalDateTime... dates) {

        /*
         Проверка на четное количество переданных дат
         */
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException("Вы должны передать четное количество дат");
        }

        /*
        Инициализация списка сегментов делим на 2 для того что бы не занимать много памяти
        и использовать ровно столько памяти сколько нужно для работы
         */
        List<SegmentEntity> segmentEntities = new ArrayList<>(dates.length / 2);

        // Обработка пар дат для создания сегментов
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segmentEntities.add(new SegmentEntity(dates[i], dates[i + 1]));
        }

        // Возвращение нового объекта FlightEntity
        return new FlightEntity(segmentEntities);
    }
}
