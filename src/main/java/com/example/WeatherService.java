package com.example;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.util.Properties;

public class WeatherService {
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=%s&appid=%s";
    private static String API_KEY;

    public static void setApiKey(String apiKey){
        WeatherService.API_KEY = apiKey;
    }

    static {
        try (InputStream input = WeatherService.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            API_KEY = prop.getProperty("weather.api.key");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load API key", e);
        }
    }

    public WeatherData getWeather(String city, String langCode) throws Exception {
        String url = String.format(API_URL, city, langCode, API_KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
        return new Gson().fromJson(response.body(), WeatherData.class);
    }
}