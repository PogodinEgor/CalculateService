package ru.pogodinegor.vacationcalc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Класс, представляющий ответ на расчет отпускных.
 * Этот класс используется для возвращения результатов расчета отпускных вместе с соответствующим сообщением.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VacationCalculationResponse {
    /**
     * Сообщение, содержащее дополнительную информацию о расчете отпускных.
     * Может включать детали о способе расчета или используемых предположениях.
     */
    private String message;

    /**
     * Рассчитанная сумма отпускных.
     * Это чистая сумма, которая должна быть выплачена сотруднику за период отпуска.
     */
    private BigDecimal vacationPay;
}
