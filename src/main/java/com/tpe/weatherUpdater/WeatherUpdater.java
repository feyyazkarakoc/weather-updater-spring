package com.tpe.weatherUpdater;

import com.tpe.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;


@Component
public class WeatherUpdater {


    private static final Logger log = LoggerFactory.getLogger(WeatherUpdater.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.weather.api.key}")
    private String apiKey;

    private Scanner scanner = new Scanner(System.in);
    private String city;


    @Scheduled(fixedRate = 10000)
    public void fetchWeather() {


        if (city == null) {
            System.out.println("Please enter the city for weather information: ");
            city = scanner.nextLine().trim();
        }


        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric&lang=ing";

        try {
            Response response = restTemplate.getForObject(url, Response.class);

            if (response != null) {
                log.info("""
                                Weather for {}: {}
                                Temperature: {}°C
                                Feels like: {}°C
                                Wind speed: {} m/s""",
                        city,
                        response.getWeather().get(0).getDescription(),
                        response.getMain().getTemp(),
                        response.getMain().getFeels_like(),
                        response.getWind().getSpeed()
                );

            }
        } catch (Exception e) {
            System.out.println("City not found");
        }
    }
}
