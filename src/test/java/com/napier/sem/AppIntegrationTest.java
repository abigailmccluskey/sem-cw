package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
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
    void testTopNContinent()
    {
        ArrayList<Country> countries = app.topNContinent(3);
        Country country = countries.get(0);
        String a = "China";
        String b = country.Name;
        assertEquals(a, b);

        country = countries.get(countries.size()-1);
        a = "Argentina";
        b = country.Name;
        assertEquals(a, b);
    }

    @Test
    void AllCountriesInRegion()
    {
        ArrayList<Country> countries = app.AllCountriesInRegion("Eastern Europe");
        Country country = countries.get(0);
        String a = "Russian Federation";
        String b = country.Name;
        assertEquals(a, b);

        String c = "Eastern Europe";
        String d = country.Region;
        assertEquals(c, d);
    }
}