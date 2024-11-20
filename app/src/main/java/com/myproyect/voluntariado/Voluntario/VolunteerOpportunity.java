package com.myproyect.voluntariado.Voluntario;

import java.util.List;

public class VolunteerOpportunity {
    private String name;
    private String description;
    private String country;
    private String city;
    private double latitude;
    private double longitude;
    private String schedule; // Nuevo campo para el horario
    private List<String> skills; // Nuevo campo para habilidades necesarias



    public VolunteerOpportunity(String name, String description, String country, String city, double latitude, double longitude, String schedule, List<String> skills) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.schedule = schedule;
        this.skills = skills;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<String> getSkills() {
        return skills;
    }

}
