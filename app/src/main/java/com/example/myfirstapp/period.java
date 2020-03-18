package com.example.myfirstapp;

public class period{
    private Integer number;
    private String name;
    private Boolean isDay;
    private Integer temp;
    private Character tempUnit;
    private String windSpeed;
    private String windDir;
    private String shortForecast;
    private String longForecast;

    public period(){};
    public period(Integer number, String name, Boolean isDay, Integer temp, Character tempUnit, String windSpeed, String windDir, String shortForecast, String longForecast){
        this.number = number;
        this.name = name;
        this.isDay = isDay;
        this.temp = temp;
        this.tempUnit = tempUnit;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.shortForecast = shortForecast;
        this.longForecast = longForecast;
    }
    public String GetForecase(){
        return longForecast;
    }
}
