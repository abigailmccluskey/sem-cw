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
        ArrayList<Country> countries = app.worldLtoS();
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

    @Test
    void AllCityInRegionLtoS(){
        ArrayList<City> cities = app.AllCityInRegionLtoS("Eastern Europe");
        City city = cities.get(0);
        int a = 8389200;
        int b = city.population;
        assertEquals(a, b);

        String c = "Moscow";
        String d = city.name;
        assertEquals(c, d);
    }

    @Test
    void AllCityInCountryLtoS(){
        ArrayList<City> cities = app.AllCityInCountryLtoS("South Africa");
        City city = cities.get(0);
        int a = 2352121;
        int b = city.population;
        assertEquals(a, b);

        String c = "Cape Town";
        String d = city.name;
        assertEquals(c, d);
    }

    @Test
    void CitiesOnContinentLtoS()
    {
        ArrayList<City> cities = app.CitiesOnContinentLtoS("Africa");

        int a = 6789479;
        int b = cities.get(0).population;
        assertEquals(a, b);

        String c = "Cairo";
        String d = cities.get(0).name;

        assertEquals(c, d);
    }

    @Test
    void CountriesContinentLtoS()
    {
        ArrayList<Country> countries = app.CountriesContinentLtoS("Europe");

        String a = "Russian Federation";
        String b = countries.get(0).Name;

        Integer c = 146934000;
        Integer d = countries.get(0).Population;

        assertEquals(a, b);
        assertEquals(c, d);
    }

    @Test
    void TopNWorld()
    {
        ArrayList<Country> countries = app.topNWorld(5);
        String a = "China";
        String b = countries.get(0).Name;
        assertEquals(a, b);

        Integer c = 1277558000;
        Integer d = countries.get(0).Population;
    }

    @Test
    void citiesInWorldLtoS()
    {
        ArrayList<City> cities = app.citiesInWorldLtoS();

        int a = 9981619;
        int b = cities.get(1).population;
        assertEquals(a, b);

        String c = "Seoul";
        String d = cities.get(1).name;

        assertEquals(c, d);
    }

    @Test
    void topNCitiesDistrict()
    {
        ArrayList<City> cities = app.topNCitiesDistrict(3, "Western Cape");

        int a = 2352121;
        int b = cities.get(0).population;
        assertEquals(a, b);

        String c = "Cape Town";
        String d = cities.get(0).name;

        assertEquals(c, d);
    }

    @Test
    void topNCitiesRegion()
    {
        ArrayList<City> cities = app.topNCitiesRegion(3, "Eastern Europe");

        int a = 8389200;
        int b = cities.get(0).population;
        assertEquals(a, b);

        String c = "Moscow";
        String d = cities.get(0).name;

        assertEquals(c, d);
    }
}