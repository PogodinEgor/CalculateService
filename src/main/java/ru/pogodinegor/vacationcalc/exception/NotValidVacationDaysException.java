package ru.pogodinegor.vacationcalc.exception;

/**
 * Исключение, выбрасываемое при некорректном количестве отпускных дней.
 */
public class NotValidVacationDaysException extends RuntimeException {
    public NotValidVacationDaysException(String message) {
        super(message);
    }
}
