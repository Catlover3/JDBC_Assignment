package se.catlover;

import se.catlover.data.CityDaoJDBC;
import se.catlover.models.City;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CityDaoJDBC DB = new CityDaoJDBC();
        DB.startConnection();
        DB.findById(40);
        DB.findByCode("AFG");  
        DB.findByName("Amsterdam");
        
        
        DB.closeConnection();
        
    }
}
