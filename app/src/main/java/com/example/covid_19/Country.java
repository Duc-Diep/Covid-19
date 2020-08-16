package com.example.covid_19;

public class Country {
    String name;
    double newConfirmed;
    double totalConfirmed;
    double newDeaths;
    double totalDeaths;
    double newRecovered;
    double totalRecovered;

    public Country(String name, double newConfirmed, double totalConfirmed, double newDeaths, double totalDeaths, double newRecovered, double totalRecovered) {
        this.name = name;
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(double newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public double getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(double totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public double getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(double newDeaths) {
        this.newDeaths = newDeaths;
    }

    public double getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(double totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public double getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(double newRecovered) {
        this.newRecovered = newRecovered;
    }

    public double getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(double totalRecovered) {
        this.totalRecovered = totalRecovered;
    }
}
