package ru.pogodinegor.vacationcalc.service;

import ru.pogodinegor.vacationcalc.dto.nager.date.api.Holiday;

import java.util.List;

/**
 * Интерфейс для сервиса, предоставляющего информацию о праздничных днях.
 */
public interface HolidayService {
    /**
     * Получает список всех праздников.
     *
     * @return Список всех праздников.
     */
    List<Holiday> getAllHolidays();
}
