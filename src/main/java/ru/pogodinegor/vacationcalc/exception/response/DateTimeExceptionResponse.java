package ru.pogodinegor.vacationcalc.exception.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ответ, содержащий информацию об ошибке, связанной с временем и датой.
 */
@Getter
@Setter
public class DateTimeExceptionResponse {

    private String message;
    private String timestamp;

    public DateTimeExceptionResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
