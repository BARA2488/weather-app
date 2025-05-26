package com.example;

import com.google.gson.annotations.SerializedName;

public class WeatherData{
private Main main;
private Wind wind;
private String name;
private Weather[] weather;

public static class Main{
    
    @SerializedName("temp")
    private double temp;
    
    private double humidity;
    
    @SerializedName("feels_like")
    private double feels_like;

    @SerializedName("pressure")
    private double pressure;


public double getTemp(){ return temp; }

public double getHumidity(){ return humidity; }

public double getFeelsLike(){ return feels_like; }

public String getPressure(){ 
    double mmHg = pressure * 0.75006;
    return String.format("%.2f", mmHg);
    }
}
public static class Wind{
    
    @SerializedName("speed")
    private double speed;

    @SerializedName("deg")
    private int direction;

    @SerializedName("gust")
    private double gust;

    public double getSpeed(){ return speed; }
    public int getDirection(){ return direction; }
    public Double getGust(){ return gust;}

    
}

public static class Weather {
    private String description;
    private String main;
    
    public String getDescription(){ return description; }
    public String getMain(){ return main; }
}

    public Main getMain(){ return main; }
    public Wind getWind(){ return wind; }
    public String getName(){ return name; }
    public Weather[] getWeather(){return weather;}
}