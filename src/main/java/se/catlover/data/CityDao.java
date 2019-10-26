package se.catlover.data;

import java.util.List;

import se.catlover.models.City;

public interface CityDao {


    public City findById (int iD);
    public List<City> findByCode(String code);
    public List<City> findByName(String name);
    public List<City> findAllCities();
    public City addCity(City city);
    public City updateCity(City city);
    public int deleteCity(City city);


    }


