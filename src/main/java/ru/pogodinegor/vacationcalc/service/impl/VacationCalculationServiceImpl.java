package ru.pogodinegor.vacationcalc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pogodinegor.vacationcalc.dto.VacationCalculationResponse;
import ru.pogodinegor.vacationcalc.exception.NotValidAverageSalaryException;
import ru.pogodinegor.vacationcalc.exception.NotValidVacationDaysException;
import ru.pogodinegor.vacationcalc.service.VacationCalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Сервис для расчета отпускных с учетом и без учета праздничных дней.
 */
@Service
@RequiredArgsConstructor
public class VacationCalculationServiceImpl implements VacationCalculationService {

    private final HolidayServiceImpl holidayServiceImpl;// Сервис для работы с праздничными днями

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;// Среднее количество дней в месяце для расчетов

    /**
     * Рассчитывает сумму отпускных с учетом праздничных дней.
     *
     * @param averageSalary Среднемесячная зарплата
     * @param vacationDays Количество дней отпуска
     * @param startVacationDay Дата начала отпуска
     * @return Расчет суммы отпускных
     */
    @Override
    public VacationCalculationResponse calculateVacationPayWithHolidays(BigDecimal averageSalary, int vacationDays, LocalDate startVacationDay) {
        validateAverageSalary(averageSalary);
        validateVacationDays(vacationDays);

        double vacationPay = averageSalary.doubleValue() / AVERAGE_DAYS_IN_MONTH * numberOfWorkingDays(vacationDays, startVacationDay);
        return new VacationCalculationResponse("Сумма отпускных за вычетом выходных и праздников", new BigDecimal(vacationPay).setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * Рассчитывает сумму отпускных без учета праздничных дней.
     *
     * @param averageSalary Среднемесячная зарплата
     * @param vacationDays Количество дней отпуска
     * @return Расчет суммы отпускных
     */
    @Override
    public VacationCalculationResponse calculateVacationPayWithoutHolidays(BigDecimal averageSalary, int vacationDays) {
        validateAverageSalary(averageSalary);
        validateVacationDays(vacationDays);

        BigDecimal vacationPay = averageSalary.multiply(BigDecimal.valueOf(vacationDays)).divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MONTH), 2, RoundingMode.HALF_UP);
        return new VacationCalculationResponse("Сумма отпускных без учета выходных и праздников", vacationPay);
    }

    /**
     * Проверяет, является ли указанная дата выходным днем или праздником.
     *
     * @param currentDate Дата для проверки.
     * @return true, если указанная дата является субботой, воскресеньем или праздником; иначе false.
     */
    public boolean isWeekendOrHoliday(LocalDate currentDate) {
        return currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY || isHoliday(currentDate);
    }

    /**
     * Рассчитывает количество рабочих дней в указанном периоде отпуска, исключая выходные и праздничные дни.
     *
     * @param vacationDays Общее количество дней отпуска.
     * @param startVacationDay Дата начала отпуска.
     * @return Количество рабочих дней в периоде отпуска.
     */
    private int numberOfWorkingDays(int vacationDays, LocalDate startVacationDay) {
        return IntStream.iterate(0, i -> i + 1)
                .limit(vacationDays + 1)
                .filter(i -> !isWeekendOrHoliday(startVacationDay.plusDays(i)))
                .map(i -> 1)
                .sum();
    }

    /**
     * Проверяет, является ли указанная дата праздничным днем.
     *
     * @param date Дата для проверки.
     * @return true, если указанная дата является праздником; иначе false.
     */
    public boolean isHoliday(LocalDate date) {
        String currentMonthDayStr = date.format(DateTimeFormatter.ofPattern("MM-dd"));

        List<String> holidaysMonthDayStr = holidayServiceImpl.getAllHolidays().stream()
                .map(holiday -> holiday.getDate().format(DateTimeFormatter.ofPattern("MM-dd")))
                .collect(Collectors.toList());

        return holidaysMonthDayStr.contains(currentMonthDayStr);
    }

    /**
     * Проверяет корректность значения среднемесячной заработной платы.
     *
     * @param averageSalary Среднемесячная заработная плата.
     * @throws NotValidAverageSalaryException если значение заработной платы меньше или равно нулю.
     */
    private void validateAverageSalary(BigDecimal averageSalary) {
        if (averageSalary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NotValidAverageSalaryException("Средняя зарплата не может быть равна нулю или быть меньше нуля!");
        }
    }
    /**
     * Проверяет корректность количества отпускных дней.
     *
     * @param vacationDays Количество отпускных дней.
     * @throws NotValidVacationDaysException если количество дней меньше 7 или больше 28.
     */
    private void validateVacationDays(int vacationDays) {
        if (vacationDays < 7 || vacationDays > 28) {
            throw new NotValidVacationDaysException("Отпускных дней не может быть меньше 7 и больше 28.");
        }
    }
}
