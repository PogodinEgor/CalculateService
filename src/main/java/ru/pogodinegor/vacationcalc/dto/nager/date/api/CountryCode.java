package ru.pogodinegor.vacationcalc.dto.nager.date.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Перечисление для представления кодов стран.
 * Поддерживает коды для России (RU), Беларуси (BY) и Украины (UA).
 */
public enum CountryCode {
    RU("RU"),
    BY("BY"),
    UA("UA");

    private final String code;

    CountryCode(String code) {
        this.code = code;
    }

    /**
     * Возвращает код страны.
     * @return Код страны в виде строки.
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Статический метод для получения экземпляра перечисления по строковому значению.
     * Если соответствие не найдено, по умолчанию возвращается RU.
     *
     * @param value Строковое представление кода страны.
     * @return Экземпляр CountryCode, соответствующий входному значению.
     */
    @JsonCreator
    public static CountryCode forValue(String value) {
        for (CountryCode countryCode : CountryCode.values()) {
            if (countryCode.getCode().equals(value)) {
                return countryCode;
            }
        }
        return RU;
    }
}
