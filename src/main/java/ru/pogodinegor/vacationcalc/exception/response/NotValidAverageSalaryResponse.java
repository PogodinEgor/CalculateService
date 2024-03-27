package ru.pogodinegor.vacationcalc.exception.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ответ, содержащий информацию о некорректном значении среднемесячной заработной платы.
 */
@Getter
@Setter
public class NotValidAverageSalaryResponse {

    private String message;
    private String timestamp;

    public NotValidAverageSalaryResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
