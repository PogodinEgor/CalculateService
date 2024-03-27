package ru.pogodinegor.vacationcalc;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@OpenAPIDefinition(
        info = @Info(
                title = "VacationCalculationService",
                description = "Микросервис 'VacationCalculationService' предназначен для расчёта суммы отпускных сотрудников. " +
                        "Основные функции включают в себя расчёт отпускных на основе средней зарплаты и количества дней отпуска, " +
                        "а также возможность учёта праздничных и выходных дней в расчётах. " +
                        "Сервис позволяет получить точную сумму отпускных, учитывая различные параметры, такие как продолжительность отпуска " +
                        "и среднемесячную заработную плату сотрудника.",
                version = "0.0.1",
                contact = @Contact(
                        name = "Pogodin Egor",
                        email = "666deadkain999@gmail.com",
                        url = "https://github.com/PogodinEgor"
                )
        )
)

@SpringBootApplication
public class VacationCalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacationCalcApplication.class, args);
    }

}
