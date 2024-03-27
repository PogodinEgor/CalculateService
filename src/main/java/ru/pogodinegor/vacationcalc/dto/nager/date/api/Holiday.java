package ru.pogodinegor.vacationcalc.dto.nager.date.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс представляет информацию о празднике, включая дату, название и другие детали.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Holiday {
    /**
     * Дата праздника. Аннотация @JsonDeserialize используется для кастомной десериализации.
     * @JsonFormat настраивает формат даты при сериализации/десериализации JSON.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String localName;
    private String name;
    private CountryCode countryCode;
    private boolean fixed;
    private boolean global;
    private String counties;
    private Integer launchYear;
    private List<String> types;
}
