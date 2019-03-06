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
        Country country = new Country();
        country.Name = "Scotland";
        country.Population = 100000;
        country.Continent = "Europe";
        countries.add(country);
        app.displayCountriesByRegion(countries);
    }
}