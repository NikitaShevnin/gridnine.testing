# Описание проекта

Данный Java проект предназначен для создания и фильтрации тестовых авиарейсов. Он включает в себя классы, 
которые позволяют создавать рейсы с сегментами, которые могут иметь разные даты отправления и прибытия, 
а также фильтровать эти рейсы по заданным правилам.

## Структура проекта

Проект состоит из нескольких пакетов:

- `com.gridnine.testing.builder` - пакет, содержащий классы для создания тестовых рейсов.
- `com.gridnine.testing.entity` - пакет, содержащий классы, представляющие сущности, такие как рейсы и сегменты.
- `com.gridnine.testing.filter` - пакет, содержащий классы для фильтрации рейсов.
- `com.gridnine.testing` - главный пакет, содержащий основной класс приложения.

## Классы и их функции

### 1. FlightBuilder

- **Метод: `createFlights()`**
    - Создает и возвращает список тестовых рейсов, включая различные сценарии с сегментами:
        - Рейс, начинающийся через 3 дня и заканчивающийся через 2 часа.
        - Рейс с несколькими промежуточными остановками.
        - Рейс, начинающийся 6 дней назад и заканчивающийся через 3 дня.
        - Рейс с недопустимым временем (начало позже конца).
        - И еще несколько вариантов с различными сценариями сегментов.

- **Метод: `createFlight(LocalDateTime... dates)`**
    - Создает объект `FlightEntity` с заданными датами отправления и прибытия.
    - Проверяет, чтобы количество переданных дат было четным. Если нет, выбрасывает `IllegalArgumentException`.

### 2. FlightEntity

- **Конструктор: `FlightEntity(List<SegmentEntity> segs)`**
    - Инициализирует объект рейса с заданным списком сегментов.

- **Метод: `getSegments()`**
    - Возвращает список сегментов рейса.

- **Метод: `toString()`**
    - Возвращает строковое представление информации о рейсе с сегментами.

### 3. SegmentEntity

- **Конструктор: `SegmentEntity(LocalDateTime dep, LocalDateTime arr)`**
    - Инициализирует объект сегмента с датой отправления и прибытия.

- **Методы:**
    - `getDepartureDate()` - возвращает дату отправления сегмента.
    - `getArrivalDate()` - возвращает дату прибытия сегмента.
    - `toString()` - возвращает строковое представление сегмента в формате "[отправление|прибытие]".

### 4. FlightFilter

- **Метод: `filterFlights(List<FlightEntity> flights)`**
    - Фильтрует список рейсов по следующим правилам:
        - Исключает рейсы с временем вылета до текущего момента.
        - Исключает рейсы, в которых есть сегменты с неверными датами (дата прибытия должна быть позже даты отправления).
        - Исключает рейсы с общим временем на земле, превышающим 2 часа.

- **Методы:**
    - `isFlightAfterCurrentTime(FlightEntity flight, LocalDateTime now)` - проверяет, что рейс начинается после 
    - текущего момента.
    - `areSegmentsValid(FlightEntity flight)` - проверяет корректность всех дат сегментов.
    - `totalGroundTimeIsValid(FlightEntity flight)` - проверяет, что общее время на земле не превышает 2 часов.

### 5. Main

- **Метод: `main(String[] args)`**
    - Основной метод приложения. Создает тестовые рейсы, фильтрует их по всем правилам и выводит результаты в консоль.
    - Сначала выводит все рейсы, затем фильтрует их по времени вылета, корректности сегментов и времени на земле, 
    - выводя результаты каждого этапа.

## Тестирование классов

Проект включает в себя набор модульных тестов, которые проверяют функциональность различных классов.

### 1. FlightBuilderTest

- **Тест: `createFlights_ShouldReturnNonEmptyList()`**
    - Проверяет, что метод `createFlights()` возвращает непустой список рейсов.

- **Тест: `createFlight_ShouldThrowExceptionForOddNumberOfDates()`**
    - Проверяет, что метод `createFlight()` выбрасывает исключение при нечётном количестве дат.

- **Тест: `createFlight_ShouldCreateFlightWithSegments()`**
    - Проверяет, что метод `createFlight()` создаёт рейс с двумя сегментами.

### 2. FlightEntityTest

- **Тест: `getSegments_ShouldReturnAllSegments()`**
    - Проверяет, что метод `getSegments()` возвращает все сегменты рейса.

- **Тест: `toString_ShouldReturnFormattedString()`**
    - Проверяет, что метод `toString()` возвращает отформатированную строку.

### 3. SegmentEntityTest

- **Тест: `constructor_WithValidParameters_ShouldCreateSegmentEntity()`**
    - Проверяет, что конструктор с корректными параметрами создаёт экземпляр `SegmentEntity`.

- **Тест: `toString_ShouldReturnFormattedString()`**
    - Проверяет, что метод `toString()` возвращает отформатированную строку.

- **Тест: `defaultConstructor_ShouldInitializeWithCurrentTime()`**
    - Проверяет, что конструктор по умолчанию инициализирует с текущим временем.

### 4. FlightFilterTest

- **Тест: `filterFlights_ShouldReturnFilteredFlights()`**
    - Проверяет, что метод `filterFlights()` возвращает отфильтрованные рейсы.

- **Тест: `isFlightAfterCurrentTime_ShouldReturnTrue()`**
    - Проверяет, что метод `isFlightAfterCurrentTime()` возвращает true для будущего рейса.

Тесты написаны с использованием библиотеки JUnit и покрывают основные функции классов, 
обеспечивая их корректность и надёжность.