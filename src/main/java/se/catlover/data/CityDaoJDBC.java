package se.catlover.data;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.catlover.models.City;

//TODO: Make separate methods to print out the data and for other stuff too

public class CityDaoJDBC implements CityDao {

    Connection connection = null;
    Connection myConnectionDemo = null;
    PreparedStatement myPreppedStmt = null;
    Statement myStmt = null;
    ResultSet myResultset = null;
    

    List<City> list = null;
    ArrayList<City> arrayList = new ArrayList<City>();
    
    public void startConnection(){

		try{
        //1. : Open a connection
        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=UTC","root","admin");
        myConnectionDemo = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC","root","admin"); // Not used

        // 2. Create a statement
        myStmt = connection.createStatement();
        
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		
    }
    
    public City findById(int id) {
    	
    	City city = new City();
    	
    	try {
		    myPreppedStmt = connection.prepareStatement("SELECT * from CITY where id = ?");      	
		    myPreppedStmt.setInt(1, id);
		    
		    ResultSet rs = myPreppedStmt.executeQuery();
		    	
		    
		    
	    	while (rs.next()){
	    		//Alternative way of doing it:	
		    	//City city2 = new City(rs.getInt("ID"), rs.getString("name"), rs.getString("CountryCode"), rs.getString("District"), rs.getInt("Population"));
		    			    			    	
		    	city.setiD(rs.getInt("ID"));
		    	city.setName(rs.getString("name"));
		    	city.setCountryCode(rs.getString("CountryCode"));
		    	city.setDistrict(rs.getString("District"));
		    	city.setPopulation(rs.getInt("Population"));
	    	}

    	} 

	    catch (SQLException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
    	
    	return city;  	
    	
    }


     
    @Override
    public List<City> findByCode(String code) {
		ArrayList<String> citiesList = new ArrayList<String>();
        City city = new City();
      	try {
		    myPreppedStmt = connection.prepareStatement("SELECT * from CITY where CountryCode = ?");      	
		    myPreppedStmt.setString(1, code);
		    
		    ResultSet rs = myPreppedStmt.executeQuery();
		    
		    //TODO: Make proper columns and rows
	    	while (rs.next()){
		    	city.setiD(rs.getInt("ID"));
		    	city.setName(rs.getString("name"));
		    	city.setCountryCode(rs.getString("CountryCode"));
		    	city.setDistrict(rs.getString("District"));
		    	city.setPopulation(rs.getInt("Population"));
		    	
		    	arrayList.add(city);
		        for(City list : arrayList) {
		        	System.out.println(list.getName());
		        	System.out.println(list.getPopulation());
		        }
	    	}
	 // 	Why not do it this way instead, using an string array??
	//    	while (rs.next()){
	//			citiesList.add(rs.getString("ID"));
	//			citiesList.add(rs.getString("name"));
	//			citiesList.add(rs.getString("CountryCode"));
	//			citiesList.add(rs.getString("District"));
	//			citiesList.add(rs.getString("Population"));
	//		}
    	}

	    catch (SQLException e) {
	    	
	    	e.printStackTrace();
	    }
		return arrayList;
    	
    }
    
    @Override
    public List<City> findByName(String name) {
    	City city = new City();
    	
      	try {
		    myPreppedStmt = connection.prepareStatement("SELECT * from CITY where name = ?");      	
		    myPreppedStmt.setString(1, name);
		    
		    ResultSet rs = myPreppedStmt.executeQuery();
		    

	    	while (rs.next()){		    			    			    	
		    	city.setiD(rs.getInt("ID"));
		    	city.setName(rs.getString("name"));
		    	city.setCountryCode(rs.getString("CountryCode"));
		    	city.setDistrict(rs.getString("District"));
		    	city.setPopulation(rs.getInt("Population"));
		    	arrayList.add(city);
	    	}
    	} 

	    catch (SQLException e) {

	    	e.printStackTrace();
	    }
		return arrayList;
    	
    }
    
    @Override
    public List<City> findAllCities() {
    	City city = new City();
    	
      	try {   
		    ResultSet rs = myStmt.executeQuery("SELECT * FROM City");
		    

	    	while (rs.next()){		    			    			    	
		    	city.setiD(rs.getInt("ID"));
		    	city.setName(rs.getString("name"));
		    	city.setCountryCode(rs.getString("CountryCode"));
		    	city.setDistrict(rs.getString("District"));
		    	city.setPopulation(rs.getInt("Population"));
		    	arrayList.add(city);
	    	}
    	} 

	    catch (SQLException e) {

	    	e.printStackTrace();
	    }
		return arrayList;
    }
    
    @Override
    public City addCity(City city) {
    	//TODO: Check if id matches another city and give error code if that happens
      	try {
	    	int iD = city.getiD();
	    	String name = city.getName();
	    	String countryCode = city.getCountryCode();
	    	String district = city.getDistrict();
	    	int pop = city.getPopulation();
	    	
	    	myPreppedStmt = connection.prepareStatement("INSERT INTO city() VALUES(?, ?, ?, ?, ?)");      	
	    	myPreppedStmt.setInt(1, iD);
	    	myPreppedStmt.setString(2, name);
	    	myPreppedStmt.setString(3, countryCode);
	    	myPreppedStmt.setString(4, district);
	    	myPreppedStmt.setInt(5, pop);
	    	
	    	myPreppedStmt.executeUpdate();  			    			    	

    	} 

	    catch (SQLException e) {

	    	e.printStackTrace();
	    }
		return city;
    	
    }
    
    @Override
    public City updateCity(City city) {
    	
      	try {
	    	int iD = city.getiD();
	    	String name = city.getName();
	    	String countryCode = city.getCountryCode();
	    	String district = city.getDistrict();
	    	int pop = city.getPopulation();
	    	
	    	myPreppedStmt = connection.prepareStatement("UPDATE city SET (?, ?, ?, ?, ?) WHERE ID ?");      	
	    	myPreppedStmt.setInt(1, iD);
	    	myPreppedStmt.setString(2, name);
	    	myPreppedStmt.setString(3, countryCode);
	    	myPreppedStmt.setString(4, district);
	    	myPreppedStmt.setInt(5, pop);
	    	myPreppedStmt.setInt(6, iD);
	    	
	    	myPreppedStmt.executeUpdate();  			    			    	
	    	
	    	
    	} 

	    catch (SQLException e) {

	    	e.printStackTrace();
	    }
		return city;
    	
    }
    	
    
    @Override
    public int deleteCity(City city) {
    	int nrOfRowsAffected = 0;
    	
      	try {
	    	int iD = city.getiD();
	    	String name = city.getName();
	    	String countryCode = city.getCountryCode();
	    	String district = city.getDistrict();
	    	int pop = city.getPopulation();
	    	
	    	myPreppedStmt = connection.prepareStatement("DELETE FROM city (?, ?, ?, ?, ?) WHERE ID ?");      	
	    	myPreppedStmt.setInt(1, iD);
	    	myPreppedStmt.setString(2, name);
	    	myPreppedStmt.setString(3, countryCode);
	    	myPreppedStmt.setString(4, district);
	    	myPreppedStmt.setInt(5, pop);
	    	myPreppedStmt.setInt(6, iD);
	    	
	    	nrOfRowsAffected = myPreppedStmt.executeUpdate();  			    			    	
	    	

    	} 

	    catch (SQLException e) {

	    	e.printStackTrace();
	    }
		return nrOfRowsAffected;
    	
    }
    
	public void closeConnection() {
  
        try{
            if(myStmt!= null)
                myStmt.close();
        }
        catch(SQLException se2){
        }
        try{
            if(connection!=null)
                connection.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
		   System.out.println("Program has finished. Exiting");
		   
	}

		

}// End of class
