package ru.pogodinegor.vacationcalc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.pogodinegor.vacationcalc.dto.nager.date.api.Holiday;
import ru.pogodinegor.vacationcalc.exception.NotConnectionDateNager;
import ru.pogodinegor.vacationcalc.service.HolidayService;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для получения информации о праздничных днях через внешний API.
 */
@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final RestTemplate restTemplate;

    @Value("${spring.url.date.nager}")
    private String HOLIDAY_URL;


    /**
     * Получает список всех праздников на текущий год для России.
     *
     * @return Список праздников.
     * @throws NotConnectionDateNager если сервис недоступен.
     */
    @Override
    public List<Holiday> getAllHolidays() {
        int currentYear = LocalDate.now().getYear();
        String url = UriComponentsBuilder.fromHttpUrl(HOLIDAY_URL)
                .path("/{year}/{countryCode}")
                .buildAndExpand(currentYear, "RU")
                .toUriString();

        ResponseEntity<List<Holiday>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Holiday>>() {
                });

        if (response.getBody() == null) {
            throw new NotConnectionDateNager("Данный сервис не доступен");
        }
        return response.getBody();
    }
}
