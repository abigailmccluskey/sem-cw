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
        app.connect("localhost:33060");
    }

    @Test
    void testPopulationLtoS()
    {
        ArrayList<Country> countries = app.populationLtoS();
        Country country = countries.get(0);

        int a = 1277558000;
        int b = country.Population;
       assertEquals(a, b);

        country = countries.get(countries.size()-1);
        a = 0;
        b = country.Population;
        assertEquals(a, b);

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
   /* @Test
    void testTopNContinent()
    {
        ArrayList<Country> countries = app.topNContinent(3);
        Country country = countries.get(0);
        int a = 0;
        int b = country.Population;
        assertEquals(a, b);
    }
*/

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