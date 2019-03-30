package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1) {
            a.connect("localhost:33060");
        } else {
            a.connect(args[0]);
        }

        //Listing world population from largest to smallest
        System.out.println("\nListing population from largest to smallest");
        ArrayList<Country> countries = a.worldLtoS();
        a.displayCountries(countries);

        //Listing top N countries per continent
        int n = 3;
        System.out.println("\nTOP " + n + " countries per continent:");
        ArrayList<Country> topNContinent = a.topNContinent(n);
        a.displayCountries(topNContinent);

        //Listing all countries in a region
        String region = "Eastern Europe";
        System.out.println("\nAll Countries in" + region + ": ");
        ArrayList<Country> AllCountriesInRegion = a.AllCountriesInRegion(region);
        a.displayCountriesByRegion(AllCountriesInRegion);

        //Listing top N countries in the world
        n = 5;
        System.out.println("\nTop "+n+" countries in the world.");
        ArrayList<Country> topNcountriesWorld = a.topNWorld(n);
        a.displayCountries(topNcountriesWorld);

        String continent = "Europe";
        System.out.println("Listing all countries on a continent.");
        ArrayList<Country> CountriesContinentsLtoS = a.CountriesContinentLtoS(continent);
        a.displayCountries(CountriesContinentsLtoS);

        //Listing all cities in the world
        System.out.println("\nListing all cities in the world");
        ArrayList<City> citiesInWorldLtoS = a.citiesInWorldLtoS();
        a.displayCities(citiesInWorldLtoS);

        //Listing all cities in a continent
        continent = "Africa";
        System.out.println("\nListing all cities in " + continent);
        ArrayList<City> citiesInContinentLtoS = a.CitiesOnContinentLtoS(continent);
        a.displayCities(citiesInContinentLtoS);

        //Listing all cities in a region
        region = "Eastern Europe";
        System.out.println("\nListing all cities in " + region);
        ArrayList<City> citiesInRegion = a.AllCityInRegionLtoS(region);
        a.displayCities(citiesInRegion);


        //Listing all cities in a country
        String country = "South Africa";
        System.out.println("\nListing all cities in " + country);
        ArrayList<City> CityInCountry = a.AllCityInCountryLtoS(country);
        a.displayCities(CityInCountry);

        n=3;
        ArrayList<City> topNpopulatedCities = a.topNpopulatedCities(n);
        a.displayCities(topNpopulatedCities);

        n=3;
        ArrayList<City> topNpopulatedCitiesInContinent = a.topNpopulatedCitiesInContinent(n);
        a.displayCities(topNpopulatedCitiesInContinent);


        //Listing all cities in a district
        n = 3;
        String district = "Western Cape";
        System.out.println("\nListing top " + n + " cities in " + district);
        ArrayList<City> topNCitiesDistrict= a.topNCitiesDistrict(n,district);
        a.displayCities(topNCitiesDistrict);

        //Listing all cities in a region
        region = "Eastern Europe";
        n = 3;
        System.out.println("\nListing Top " + n + " cities in " + region);
        ArrayList<City> topNCitiesRegion= a.topNCitiesRegion(n,region);
        a.displayCities(topNCitiesRegion);

        //Listing Top N Capital Cities
        n = 5;
        System.out.println("\nListing Top " + n + " cities in the world.");
        ArrayList<City> topNCitiesWorld = a.topNCapitalWorld(n);
        a.displayCities(topNCitiesWorld);

        System.out.println("Listing all capital cities from largest population to smallest.");
        ArrayList<City> CitiesLtoS = a.citiesInWorldLtoS();
        a.displayCities(CitiesLtoS);
        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<Country> worldLtoS() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Continent, Population "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Population = rset.getInt("Population");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    /**
     * Prints a list of countries.
     *
     * @param countries The list of employees to print.
     */
    public void displayCountries(ArrayList<Country> countries) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s", "Name", "Continent", "Population"));
        // Loop over all employees in the list
        for (Country country : countries) {
            String emp_string =
                    String.format("%-10s %-15s %-20s",
                            country.Name, country.Continent, country.Population);
            System.out.println(emp_string);
        }
    }

    public void displayCountriesByRegion(ArrayList<Country> countries) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s", "Name", "Region", "Population"));
        // Loop over all employees in the list
        for (Country country : countries) {
            String emp_string =
                    String.format("%-10s %-15s %-20s",
                            country.Name, country.Region, country.Population);
            System.out.println(emp_string);
        }
    }


    public ArrayList<Country> topNContinent(int n) {
        try {
            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};

            ArrayList<Country> countries = new ArrayList<Country>();

            for (String cont : continents) {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT Name, Continent, Population "
                                + "FROM country "
                                + "WHERE Continent = '" + cont + "' "
                                + "ORDER BY Population DESC LIMIT " + n;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract employee information

                while (rset.next()) {
                    Country country = new Country();
                    country.Name = rset.getString("Name");
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getInt("Population");
                    countries.add(country);
                }
            }


            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<Country> AllCountriesInRegion(String region) {
        try {

            ArrayList<Country> countries = new ArrayList<Country>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT Name, Region, Population "
                            + "FROM country "
                            + "WHERE Region = '" + region + "' "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City> citiesInWorldLtoS()
    {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, District, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public void displayCities(ArrayList<City> cities) {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s", "Name", "District", "Population"));
        // Loop over all employees in the list
        for (City city : cities) {
            String emp_string =
                    String.format("%-10s %-15s %-20s",
                            city.name, city.district, city.population);
            System.out.println(emp_string);
        }
    }

    public ArrayList<Country> topNWorld(int n)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT Name, Continent, Population "
                            +"FROM country "
                            +"ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<Country> topNcountries = new ArrayList<Country>();
            while(rset.next())
            {
                Country country = new Country();
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Population = rset.getInt("Population");
                topNcountries.add(country);
            }
            return topNcountries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<Country> CountriesContinentLtoS(String passedCountry)
    {
        try
        {
            String [] continents = new String[] {"Asia","Europe","North America","Africa","Oceania","Antarctica","South America"};

            ArrayList<Country> countries = new ArrayList<Country>();

            for(String cont : continents){
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT Name, Continent, Population "
                                + "FROM country "
                                + "WHERE Continent = '" + passedCountry +"' "
                                + "ORDER BY Population DESC  " ;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract employee information

                while (rset.next())
                {
                    Country country = new Country();
                    country.Name = rset.getString("Name");
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getInt("Population");
                    countries.add(country);
                }
            }


            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }


    }

    public ArrayList<City> CitiesOnContinentLtoS(String continent)
    {
        try
        {
            String [] continents = new String[] {"Asia","Europe","North America","Africa","Oceania","Antarctica","South America"};

            ArrayList<City> cities = new ArrayList<>();

            for(String cont : continents){
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT city.Name, city.District, city.Population "
                                + "FROM city, country "
                                + "WHERE country.Continent = '" + continent +"' "
                                + "AND country.Code = city.CountryCode "
                                + "ORDER BY Population DESC  " ;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Extract employee information

                while (rset.next())
                {
                    City city = new City();
                    city.name = rset.getString("city.Name");
                    city.district = rset.getString("city.District");
                    city.population = rset.getInt("city.Population");
                    cities.add(city);
                }
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City>AllCityInRegionLtoS(String region)
    {
        try {

            ArrayList<City> cities = new ArrayList<City>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Region = '" + region +"' "
                            + "AND  city.CountryCode = country.Code "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City>AllCityInCountryLtoS(String country)
    {
        try {

            ArrayList<City> cities = new ArrayList<City>();

            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =

                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Name = '" + country +"' "
                            + "AND  city.CountryCode = country.Code "
                            + "ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }


    public ArrayList<City>topNpopulatedCities(int n)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT Name, District,Population "
                            +"FROM city "
                            +"ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> topNpopulationCities = new ArrayList<City>();
            while(rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                topNpopulationCities.add(city);
            }
            return topNpopulationCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City>topNpopulatedCitiesInContinent(int n)
    {
        try
        {

            String[] continents = new String[]{"Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"};
            ArrayList<City> topNpopulationCities = new ArrayList<City>();

            for (String cont : continents)
            {
                Statement stmt = con.createStatement();
                String strtopNWorld =
                        "SELECT city.Name,city.District, city.Population "
                                + "FROM city,country "
                                + "WHERE Continent = '" + cont+"' "
                                + "AND city.CountryCode = country.code "
                                + "ORDER BY Population DESC LIMIT " + n;

                ResultSet rset = stmt.executeQuery(strtopNWorld);

                while (rset.next()) {
                    City city = new City();
                    city.name = rset.getString("Name");
                    city.district=rset.getString("District");
                    city.population = rset.getInt("Population");
                    topNpopulationCities.add(city);
                }
            }
            return topNpopulationCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }
    public ArrayList<City> topNCitiesDistrict(int n, String district)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT Name, District, Population "
                            +"FROM city "
                            +"WHERE District = '" + district +"' "
                            +"ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> topNcites = new ArrayList<City>();
            while(rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                topNcites.add(city);
            }
            return topNcites;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City> topNCapitalWorld(int n)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT city.Name, District, Population "
                            +"FROM country, city "
                            +"WHERE city.ID = country.Capital "
                            +"ORDER BY city.Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> topNCities= new ArrayList<City>();
            while(rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                topNCities.add(city);
            }
            return topNCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City> topNCitiesRegion(int n, String region)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strtopNWorld =
                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE Region = '" + region + "' "
                            + "AND city.CountryCode = country.Code "
                            + "ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strtopNWorld);
            ArrayList<City> topNcites = new ArrayList<City>();
            while(rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                topNcites.add(city);
            }
            return topNcites;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    public ArrayList<City> CapitalsWorldLtoS(int n)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strLtoS =
                    "SELECT Name, District, Population"
                            +"FROM city "
                            +"ORDER BY Population DESC LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strLtoS);
            ArrayList<City> LtoS = new ArrayList<City>();
            while(rset.next())
            {
                City city = new City();
                city.name = rset.getString("Name");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                LtoS.add(city);
            }
            return LtoS;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }
}