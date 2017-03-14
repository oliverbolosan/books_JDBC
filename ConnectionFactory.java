import java.sql.*;
import java.util.Scanner;

public class ConnectionFactory 
{
    //  Database credentials
    static String USER = "ollie";
    static String PASS = "taco";
    static String DBNAME = "JDBC";
    //This is the specification for the printout that I'm doing:
    //each % denotes the start of a new field.
    //The - denotes left justification.
    //The number indicates how wide to make the field.
    //The "s" denotes that it's a string.  All of our output in this test are
    //strings, but that won't always be the case.
// JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
    
    public static Connection getConnection()
    {
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        Connection conn = null; //initialize the connection
        Statement stmt = null;  //initialize the statement that we're using
        try 
        {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL);
        }
        catch (SQLException se) 
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e)
        {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return conn;
    }
}
