package ru.pogodinegor.vacationcalc.exception;

/**
 * Исключение, выбрасываемое при некорректном значении среднемесячной заработной платы.
 */
public class NotValidAverageSalaryException extends RuntimeException {
    public NotValidAverageSalaryException(String message) {
        super(message);
    }
}
