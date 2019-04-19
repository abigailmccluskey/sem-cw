package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.expression.spel.ast.NullLiteral;

import javax.validation.constraints.Null;
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
        Country country3 = new Country();
        country1.Name = "Scotland";
        country1.Population = 100000;
        country1.Continent = "Europe";
        country1.Region = "Northern Europe";

        country2.Name = "England";
        country2.Population = 1000000;
        country2.Continent = "Europe";
        country2.Region = "Northern Europe";

        country3.Name = null;
        country3.Population = 0;
        country3.Continent = null;
        country3.Region = null;

        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
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

    @Test
    void displayCitiesByCountry()
    {
        ArrayList<City> cities = new ArrayList<>();
        City city1 = new City();
        City city2 = new City();
        city1.name = "Edinburgh";
        city1.district = "West Lothian";
        city1.CountryCode = "GBR";
        city1.population = 100000;

        city2.name = null;
        city2.district = null;
        city2.CountryCode = null;
        city2.population = 0;

        cities.add(city1);
        cities.add(city2);
        app.displayCitiesByCountry(cities);
    }
    @Test
    public void displayPop(){
        ArrayList<Population> pops = new ArrayList<>();
        Population pop1 = new Population();
        Population pop2 = new Population();
        pop1.continent =  "North America";
        pop1.pop = "482993000";
        pop1.cityPopulation = "168250381";
        pop1.nonCityPopulation = "314742619";

        pop2.continent =  null;
        pop2.pop = null;
        pop2.cityPopulation = null;
        pop2.nonCityPopulation = null;

        pops.add(pop1);
        pops.add(pop2);
        app.displayPop(pops);
    }
}