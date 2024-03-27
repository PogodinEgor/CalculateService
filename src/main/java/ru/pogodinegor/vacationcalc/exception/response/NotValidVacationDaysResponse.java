package ru.pogodinegor.vacationcalc.exception.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ответ, содержащий информацию о некорректном количестве отпускных дней.
 */
@Getter
@Setter
public class NotValidVacationDaysResponse {

    private String message;
    private String timestamp;

    public NotValidVacationDaysResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
