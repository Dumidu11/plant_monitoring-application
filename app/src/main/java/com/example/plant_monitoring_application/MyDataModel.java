package com.example.plant_monitoring_application;

public class MyDataModel {

    private String temprature;

    private String humidity;

    private String moisture;

    private String rain;

    private String light_intencity;

    private String date;

    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getLight_intencity() {
        return light_intencity;
    }

    public void setLight_intencity(String light_intencity) {
        this.light_intencity = light_intencity;
    }

    public String getMoisture() {
        return moisture;
    }

    public void setMoisture(String moisture) {
        this.moisture = moisture;
    }

    public String getTemprature() {
        return temprature;
    }

    public void setTemprature(String temprature) {
        this.temprature = temprature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
