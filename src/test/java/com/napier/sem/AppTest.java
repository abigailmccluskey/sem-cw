package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void displayCountries()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.Name = "Scotland";
        country.Population = 100000;
        country.Continent = "Europe";
        countries.add(country);
        app.displayCountries(countries);
    }

    @Test
    void displayCountriesByRegion()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country country1 = new Country();
        Country country2 = new Country();
        country1.Name = "Scotland";
        country1.Population = 100000;
        country1.Continent = "Europe";
        country1.Region = "Northern Europe";

        country2.Name = "England";
        country2.Population = 1000000;
        country2.Continent = "Europe";
        country2.Region = "Northern Europe";

        countries.add(country1);
        countries.add(country2);
        app.displayCountriesByRegion(countries);
    }

    @Test
    void displayCitiesTest()
    {
        ArrayList<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.name = "Edinburgh";
        city1.district = "West Lothian";
        city1.population = 100000;

        cities.add(city1);
        app.displayCities(cities);
    }
}