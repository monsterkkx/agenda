package com.example.agenda;

public class Appointment {

    private String serviceName;
    private String date;
    private String time;

    public Appointment() {
        //constructor vazio necessario para o firebase
    }

    public Appointment(String serviceName, String date, String time) {
        this.serviceName = serviceName;
        this.date = date;
        this.time = time;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

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
}
