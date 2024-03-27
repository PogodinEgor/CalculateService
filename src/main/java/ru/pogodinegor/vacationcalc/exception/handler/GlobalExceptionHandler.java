package ru.pogodinegor.vacationcalc.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.pogodinegor.vacationcalc.exception.NotConnectionDateNager;
import ru.pogodinegor.vacationcalc.exception.NotValidAverageSalaryException;
import ru.pogodinegor.vacationcalc.exception.NotValidVacationDaysException;
import ru.pogodinegor.vacationcalc.exception.response.DateTimeExceptionResponse;
import ru.pogodinegor.vacationcalc.exception.response.NotConnectionDateNagerResponse;
import ru.pogodinegor.vacationcalc.exception.response.NotValidAverageSalaryResponse;
import ru.pogodinegor.vacationcalc.exception.response.NotValidVacationDaysResponse;

import java.time.DateTimeException;

/**
 * Глобальный обработчик исключений для приложения.
 * Отлавливает различные типы исключений, выбрасываемые в процессе выполнения приложения,
 * и возвращает соответствующие ответы клиенту.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключения типа DateTimeException, возникающие при работе с неверными датами.
     *
     * @param ex Исключение, возникшее в ходе выполнения.
     * @return Ответ, содержащий сообщение об ошибке и статус NOT_FOUND.
     */
    @ExceptionHandler
    public ResponseEntity<DateTimeExceptionResponse> handleException(DateTimeException ex) {
        DateTimeExceptionResponse response = new DateTimeExceptionResponse("Указана неверная дата!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключения, связанные с невозможностью подключения к сервису Date Nager.
     *
     * @param ex Исключение, содержащее сообщение о недоступности сервиса.
     * @return Ответ, содержащий сообщение об ошибке и статус NOT_FOUND.
     */
    @ExceptionHandler
    public ResponseEntity<NotConnectionDateNagerResponse> handleException(NotConnectionDateNager ex) {
        NotConnectionDateNagerResponse response = new NotConnectionDateNagerResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключения, связанные с некорректным значением среднемесячной заработной платы.
     *
     * @param ex Исключение, содержащее информацию о некорректности средней заработной платы.
     * @return Ответ, содержащий сообщение об ошибке и статус NOT_FOUND.
     */
    @ExceptionHandler
    public ResponseEntity<NotValidAverageSalaryResponse> handleException(NotValidAverageSalaryException ex) {
        NotValidAverageSalaryResponse response = new NotValidAverageSalaryResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключения, связанные с недопустимым количеством отпускных дней.
     *
     * @param ex Исключение, содержащее информацию о некорректном количестве отпускных дней.
     * @return Ответ, содержащий сообщение об ошибке и статус NOT_FOUND.
     */
    @ExceptionHandler
    public ResponseEntity<NotValidVacationDaysResponse> handleException(NotValidVacationDaysException ex) {
        NotValidVacationDaysResponse response = new NotValidVacationDaysResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
