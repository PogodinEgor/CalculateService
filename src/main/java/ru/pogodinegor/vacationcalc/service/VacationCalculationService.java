package ru.pogodinegor.vacationcalc.service;

import ru.pogodinegor.vacationcalc.dto.VacationCalculationResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс для сервиса, выполняющего расчет отпускных.
 */
public interface VacationCalculationService {
    /**
     * Рассчитывает сумму отпускных с учетом праздничных дней.
     *
     * @param averageSalary Среднемесячная заработная плата сотрудника.
     * @param vacationDays Количество дней отпуска.
     * @param startVacationDay Дата начала отпуска.
     * @return Ответ с расчетом отпускных.
     */
    VacationCalculationResponse calculateVacationPayWithHolidays(BigDecimal averageSalary, int vacationDays, LocalDate startVacationDay);

    /**
     * Рассчитывает сумму отпускных без учета праздничных дней.
     *
     * @param averageSalary Среднемесячная заработная плата сотрудника.
     * @param vacationDays Количество дней отпуска.
     * @return Ответ с расчетом отпускных.
     */
    VacationCalculationResponse calculateVacationPayWithoutHolidays(BigDecimal averageSalary, int vacationDays);
}
