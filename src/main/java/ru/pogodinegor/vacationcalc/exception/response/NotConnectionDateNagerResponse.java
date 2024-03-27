package ru.pogodinegor.vacationcalc.exception.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ответ, содержащий информацию об ошибке при подключении к сервису Date Nager.
 */
@Getter
@Setter
public class NotConnectionDateNagerResponse {

    private String message;
    private String timestamp;

    public NotConnectionDateNagerResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
