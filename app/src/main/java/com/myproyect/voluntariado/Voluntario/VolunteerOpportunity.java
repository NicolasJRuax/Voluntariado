package com.myproyect.voluntariado.Voluntario;

public class VolunteerOpportunity {
    private String name;
    private String description;
    private String country;
    private String city;
    private double latitude;
    private double longitude;

    public VolunteerOpportunity(String name, String description, String country, String city, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
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
}
