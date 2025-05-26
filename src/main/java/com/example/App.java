package com.example;

import java.util.Scanner;
import com.example.WeatherService;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class App {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String language = null;
        
        System.out.println("-Description-\nНазвание города вводить на английском.\nEnter the name of the city in English.");
        
        System.out.print("Insert your api key: ");
        String apiKey = scanner.nextLine();
        WeatherService.setApiKey(apiKey);
        
        WeatherService weatherService = new WeatherService();
        
        System.out.println("Change language: en/ru");
        language = scanner.nextLine();
        boolean isRussian = language.contains("ru");
        
        while (true) {
            if(isRussian){
            System.out.print("Введите город: ");
            }
            else if (!isRussian){
            System.out.print("Enter the name of the city: ");
            }
            String city = scanner.nextLine();
            if("exit".equalsIgnoreCase(city)){
                break;
            }
        
        
            try{
            WeatherData data = weatherService.getWeather(city, language);
             
            if(isRussian){
            System.out.println("\nПогода в " + data.getName() + ":");
            System.out.printf("Температура: %.1f°C%n", data.getMain().getTemp());
            System.out.printf("Ощущается как: %.1f°C%n", data.getMain().getFeelsLike());
            System.out.println("Влажность: " + data.getMain().getHumidity() + "%");
            System.out.println("Давление: " + data.getMain().getPressure() + " мм рт. ст.");
            System.out.println("Описание: " + data.getWeather()[0].getDescription());
            System.out.println("Скорость ветра: " + data.getWind().getSpeed() + " м/с");
            System.out.println("Направление ветра: " + data.getWind().getDirection() + "°");
            if(data.getWind().getGust() != null){
                System.out.println("Порывы ветра: " + data.getWind().getGust() + " м/с");
            }

            }
            else if(!isRussian){
            System.out.println("\nWeather in " + data.getName() + ":");
            System.out.printf("Temperature: %.1f°C%n", data.getMain().getTemp());
            System.out.printf("Feels like: %.1f°C%n", data.getMain().getFeelsLike());
            System.out.println("Humidity: " + data.getMain().getHumidity() + "%");
            System.out.println("Pressure: " + data.getMain().getPressure() + " mmHg");
            System.out.println("Description: " + data.getWeather()[0].getDescription());
            System.out.println("Wind speed: " + data.getWind().getSpeed() + " m/s");
            System.out.println("Wind direction: " + data.getWind().getDirection() + "°");
            if (data.getWind().getGust() != null) {
                System.out.println("Wind gusts: " + data.getWind().getGust() + " m/s");
            }
        }
    } 
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        } 
    }
    scanner.close();
    }
}
