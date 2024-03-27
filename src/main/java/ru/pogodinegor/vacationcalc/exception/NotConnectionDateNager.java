package ru.pogodinegor.vacationcalc.exception;

/**
 * Исключение, выбрасываемое при невозможности подключения к сервису Date Nager.
 */
public class NotConnectionDateNager extends RuntimeException {
    public NotConnectionDateNager(String message) {
        super(message);
    }
}
