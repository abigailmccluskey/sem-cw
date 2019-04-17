package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060");
    }

    @AfterAll
    static void dc() {
        app.disconnect();
    }

    @Test
    void testPopulationLtoS() {
        ArrayList<Country> countries = app.worldLtoS();
        Country country = countries.get(0);

        int a = 1277558000;
        int b = country.Population;
        assertEquals(a, b);

        country = countries.get(countries.size() - 1);
        a = 0;
        b = country.Population;
        assertEquals(a, b);
    }

    @Test
    void testTopNContinent() {
        ArrayList<Country> countries = app.topNContinent(3);
        Country country = countries.get(0);
        String a = "China";
        String b = country.Name;
        assertEquals(a, b);

        country = countries.get(countries.size() - 1);
        a = "Argentina";
        b = country.Name;
        assertEquals(a, b);
    }

    @Test
    void AllCountriesInRegion() {
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
    void AllCityInRegionLtoS() {
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
    void AllCityInCountryLtoS() {
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
    void CitiesOnContinentLtoS() {
        ArrayList<City> cities = app.CitiesOnContinentLtoS("Africa");

        int a = 6789479;
        int b = cities.get(0).population;
        assertEquals(a, b);

        String c = "Cairo";
        String d = cities.get(0).name;

        assertEquals(c, d);
    }

    @Test
    void CountriesContinentLtoS() {
        ArrayList<Country> countries = app.CountriesContinentLtoS("Europe");

        String a = "Russian Federation";
        String b = countries.get(0).Name;

        Integer c = 146934000;
        Integer d = countries.get(0).Population;

        assertEquals(a, b);
        assertEquals(c, d);
    }

    @Test
    void TopNWorld() {
        ArrayList<Country> countries = app.topNWorld(5);
        String a = "China";
        String b = countries.get(0).Name;
        assertEquals(a, b);

        Integer c = 1277558000;
        Integer d = countries.get(0).Population;

        assertEquals(c,d);
    }

    @Test
    void citiesInWorldLtoS() {
        ArrayList<City> cities = app.citiesInWorldLtoS();

        int a = 10500000;
        int b = cities.get(0).population;
        assertEquals(a, b);

        String c = "Mumbai (Bombay)";
        String d = cities.get(1).name;

        assertEquals(c, d);
    }

    @Test
    void topNCitiesDistrict() {
        ArrayList<City> cities = app.topNCitiesDistrict(3, "Western Cape");

        int a = 2352121;
        int b = cities.get(0).population;
        assertEquals(a, b);

        String c = "Cape Town";
        String d = cities.get(0).name;

        assertEquals(c, d);
    }

    @Test
    void topNCitiesRegion() {
        ArrayList<City> cities = app.topNCitiesRegion(3, "Eastern Europe");

        int a = 8389200;
        int b = cities.get(0).population;
        assertEquals(a, b);

        String c = "Moscow";
        String d = cities.get(0).name;

        assertEquals(c, d);
    }

    @Test
    void TopNpopulatedCities() {
        ArrayList<City> city = app.topNpopulatedCities(4);
        String a = "Mumbai (Bombay)";
        String b = city.get(0).name;
        assertEquals(a, b);

        Integer c = 10500000;
        Integer d = city.get(0).population;
        assertEquals(c,d);
    }

    @Test
    void TopNpopulatedCitiesInContinent() {
        ArrayList<City> city = app.topNpopulatedCitiesInContinent(5);
        String a = "Mumbai (Bombay)";
        String b = city.get(0).name;
        assertEquals(a, b);

        Integer c = 10500000;
        Integer d = city.get(0).population;
        assertEquals(c,d);
    }

    @Test
    void CapitalsContinentLtoS() {
        ArrayList<City> city = app.CapitalsContinentLtoS("Europe");
        String a = city.get(0).name;
        ;
        String b = "Moscow";
        assertEquals(a, b);

        Integer c = 8389200;
        Integer d = city.get(0).population;
        assertEquals(c, d);
    }

    @Test
    void CapitalsRegionLtoS() {
        ArrayList<City> city = app.CapitalsRegionLtoS("Western Europe");
        String a = city.get(0).name;
        String b = "Berlin";
        assertEquals(a, b);

        Integer c = 3386667;
        Integer d = city.get(0).population;
        assertEquals(c, d);
    }

    @Test
    void topNCapitalWorld() {
        ArrayList<City> city = app.topNCapitalWorld(5);
        String a = city.get(0).name;
        String b = "Seoul";
        assertEquals(a, b);

        int c = city.get(0).population;
        int d = 9981619;
        assertEquals(c,d);
    }

    @Test
    void CapitalsWorldLtoS() {
        ArrayList<City> city = app.CapitalsWorldLtoS();
        String a = city.get(0).name;
        String b = "Seoul";
        assertEquals(a, b);

        int c = city.get(0).population;
        int d = 9981619;
        assertEquals(c, d);
    }

    @Test
    void topNpopulatedCitiesInCountry() {
        ArrayList<City> city = app.topNpopulatedCitiesInCountry(5, "Germany");
        String a = city.get(0).name;
        String b = "Berlin";
        assertEquals(a, b);

        int c = city.get(0).population;
        int d = 3386667;
        assertEquals(c, d);
    }

    @Test
    void AllCityInDistrictLtoS()
    {
        ArrayList<City> city = app.AllCityInDistrictLtoS("Zuid-Holland");
        ArrayList<City> test = app.AllCityInDistrictLtoS(null);

        String a = city.get(0).name;
        String b = "Rotterdam";
        assertEquals(a, b);

        int c = city.get(0).population;
        int d = 593321;
        assertEquals(c, d);
    }

    @Test
    void topNCapitalContinent()
    {
        ArrayList<City> city = app.topNCapitalContinent(5);

        String a = city.get(2).name;
        String b = "Tokyo";
        assertEquals(a, b);

        int c = city.get(2).population;
        int d = 7980230;
        assertEquals(c, d);
    }
    @Test
    void populationPerContinent(){
        ArrayList<Population> populationPerContinent = app.populationPerContinent();

        String a = populationPerContinent.get(0).continentPopulation;
        String b = "1970128447000";
        assertEquals(a, b);

        String c = populationPerContinent.get(0).cityPopulation;
        String d = "52893715708";
        assertEquals(c, d);
    }
    @Test
    void givens()
    {
        Given given = new Given();
        given = app.populationContinentRegionCountryDistrictCity("Africa", "Eastern Europe", "Poland", "Scotland", "Tokyo");

        long world = 6078749450L;
        long continent = 784475000;
        int country = 38653600;

        assertEquals(world, given.worldPopulation);
        assertEquals(continent, given.continentPopulation);
        assertEquals(country, given.countryPopulation);
    }

    @Test
    void TopNpopulatedCapitalCitiesInRegion()
    {
        ArrayList<City> capitals = app.TopNpopulatedCapitalCitiesInRegion(5, "Eastern Europe");
        String name1 = "Moscow";
        int population1 = 8389200;

        assertEquals(name1, capitals.get(0).name);
        assertEquals(population1, capitals.get(0).population);
    }

    @Test
    void languages()
    {
        ArrayList<countryLanguage> languages = app.languages();
        String name1 = "Chinese";
        double percentage = 19.0;
        assertEquals(languages.get(0).Language, name1);
        assertEquals(languages.get(0).Percentage, percentage);
    }

    @Test
    void topNcountriesRegion()
    {
        ArrayList<Country> countries = app.topNcountriesRegion("Eastern Europe", 5);
        Country country = countries.get(0);
        String a = "Russian Federation";
        String b = country.Name;
        assertEquals(a, b);

        String c = "Eastern Europe";
        String d = country.Region;
        assertEquals(c, d);

        int e = country.Population;
        int f = 146934000;
        assertEquals(e,f);
    }
    @Test
    void TopNCitiesCountry()
    {
        ArrayList<City> city = app.TopNCitiesCountry("South Africa", 5);

        String a = city.get(0).name;
        String b = "Cape Town";
        assertEquals(a, b);

        int c = city.get(0).population;
        int d = 2352121;
        assertEquals(c, d);
    }
}