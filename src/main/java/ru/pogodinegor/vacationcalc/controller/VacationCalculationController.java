package ru.pogodinegor.vacationcalc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pogodinegor.vacationcalc.service.impl.VacationCalculationServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

@Tag(name = "Vacation Calculation Controller ",
        description = "Контроллер для получения суммы отпускных. Позволяет получать отпускные за вычетом выходных и праздников, без учета выходных и праздников.")
@RestController
@RequiredArgsConstructor
public class VacationCalculationController {
    private final VacationCalculationServiceImpl vacationCalculationServiceImpl;

    /**
     * Рассчитывает сумму отпускных на основе среднемесячной зарплаты, количества дней отпуска и, опционально, даты начала отпуска.
     * Если дата начала отпуска указана, расчет учитывает выходные и праздничные дни.
     *
     * @param averageSalary Среднемесячная зарплата.
     * @param vacationDays Количество дней отпуска.
     * @param startVacationDay Дата начала отпуска (необязательный параметр).
     * @return Ответ с расчетом суммы отпускных.
     */
    @Operation(summary = "Расчет суммы отпускных",
            description = "Позволяет рассчитать сумму отпускных на основе среднемесячной зарплаты, количества дней отпуска и, опционально, даты начала отпуска. " +
                    "Если дата начала отпуска указана, расчет учитывает выходные и праздничные дни.")
    @GetMapping("/calculate")  //в задании указан маппинг /calculacte (с ошибкой)
    public ResponseEntity<?> getVacationPay(
            @NonNull @RequestParam("averageSalary") BigDecimal averageSalary,
            @NonNull @RequestParam("vacationDays") Integer vacationDays,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startVacationDay) {

        if (startVacationDay != null) {
            return ResponseEntity.ok(vacationCalculationServiceImpl.calculateVacationPayWithHolidays(averageSalary, vacationDays, startVacationDay));
        }
        return ResponseEntity.ok(vacationCalculationServiceImpl.calculateVacationPayWithoutHolidays(averageSalary, vacationDays));
    }
}
