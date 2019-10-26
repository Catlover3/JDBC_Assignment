package se.catlover.data;

public class DB_Connection {

    Connection myConnectionWorld = null;
    Connection myConnectionDemo = null;
    PreparedStatement myPreppedStmt = null;
    Statement myStmt = null;
    ResultSet myResultset = null;
    int rowsAffected = 0;


		try{
        //1. : Open a connection
        System.out.println("Connecting to database...");
        myConnectionWorld = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false&serverTimezone=UTC","root","admin");
        myConnectionDemo = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC","root","admin");

        // 2. Create a statement
        myStmt = myConnectionDemo.createStatement();
        myPreppedStmt = myConnectionDemo.prepareStatement("delete from employees where first_name = ?");

        myPreppedStmt.setString(1, "David");

        // 3. Execute SQL query
        String sql = "insert into employees " + " (last_name, first_name, email)"
                + " values ('John', 'Johnsson', 'j.j@foo.com')";

//	            myStmt.executeUpdate(sql);

        rowsAffected = myPreppedStmt.executeUpdate();
        int rowsAdded = myStmt.executeUpdate(sql);

        System.out.println("Insert complete. " + "Rows affected: " + rowsAffected +
                "\nRows added: " + rowsAdded );

    }catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
    }catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
    }finally{
        //finally block used to close resources
        try{
            if(myStmt!=null)
                myStmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
            if(myConnectionWorld!=null)
                myConnectionWorld.close();
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
    }//end try
		   System.out.println("Program has finished. Exiting");








}
