package gr.mfa.alfasigma;

import gr.mfa.alfasigma.data.AlfaSigma;
import gr.mfa.alfasigma.data.DataFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MsAccessDatabaseConnection {

    public static void main(String[] args) {

        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // Step 1: Loading or registering Oracle JDBC driver class
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {

            System.out.println("Problem in loading or registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection
        try {

            String msAccessDBName = "C:\\dt_files\\AS\\dbase\\AS-2018.mdb";
            String dbURL = "jdbc:ucanaccess://" + msAccessDBName;

            String UserName = "admin";
            String PassWord = "xalfasigmax";

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL, UserName, PassWord);


            String sql = "select count(*) as EISER from AlfaSigma where ASE is not null";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);



            while (rs.next()) {
                int yearIncoming = Integer.parseInt(rs.getString("EISER"));
                System.out.println(" --> Incoming Records" + " " + yearIncoming);
            }
            rs.close();
            stmt.close();

            // Step 2.B: Creating JDBC Statement
            statement = connection.createStatement();

            // Step 2.C: Executing SQL & retrieve data into ResultSet
            resultSet = statement.executeQuery("SELECT * FROM AlfaSigma where ASE is not null");

            System.out.println("ID\tName\t\t\tAge\tMatches");
            System.out.println("==\t================\t===\t=======");

            // processing returned data and printing into console
            while(resultSet.next()) {

                AlfaSigma as = DataFactory.get(resultSet);

                System.out.println(as.toString());

            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {

            // Step 3: Closing database connection
            try {
                if(null != connection) {

                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();

                    // and then finally close connection
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
}