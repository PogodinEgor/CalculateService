package ru.pogodinegor.vacationcalc;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.pogodinegor.vacationcalc.dto.VacationCalculationResponse;
import ru.pogodinegor.vacationcalc.dto.nager.date.api.CountryCode;
import ru.pogodinegor.vacationcalc.dto.nager.date.api.Holiday;
import ru.pogodinegor.vacationcalc.service.impl.HolidayServiceImpl;
import ru.pogodinegor.vacationcalc.service.impl.VacationCalculationServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class VacationCalculationServiceImplTests {


    @Test
   void testCalculateVacationPayWithHolidays() {
        HolidayServiceImpl holidayServiceMock = Mockito.mock(HolidayServiceImpl.class);

        when(holidayServiceMock.getAllHolidays()).thenReturn(Arrays.asList(
                new Holiday(LocalDate.of(2024, 2, 23)
                        , "День защитника Отечества", "Defender of the Fatherland Day"
                        , CountryCode.RU, false, true
                        , null, null, Arrays.asList("Public"))
        ));

        BigDecimal averageSalary = new BigDecimal("1000");
        int vacationDays = 7;
        LocalDate startVacationDay = LocalDate.of(2024, 2, 19);

        VacationCalculationServiceImpl service = new VacationCalculationServiceImpl(holidayServiceMock);


        VacationCalculationResponse response = service.calculateVacationPayWithHolidays(averageSalary, vacationDays, startVacationDay);

        // Проверка
        BigDecimal expectedVacationPay = new BigDecimal("170.65");
        assertEquals(expectedVacationPay, response.getVacationPay());

    }

    @Test
    void calculateVacationPayWithoutHolidaysTest() {

        VacationCalculationServiceImpl service = new VacationCalculationServiceImpl(null);
        BigDecimal averageSalary = new BigDecimal("1000");
        int vacationDays = 10;


        VacationCalculationResponse response = service.calculateVacationPayWithoutHolidays(averageSalary, vacationDays);


        BigDecimal expectedVacationPay = new BigDecimal("341.30");
        assertEquals(expectedVacationPay, response.getVacationPay());
    }
}
