package edu.khai.sa.deiko;
//STEP 1. Import required packages
import java.sql.*;


public class Lab1 {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/java";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    private Connection connect()
    {
        Connection conn = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    private void doSelect(Connection conn)
    {
        Statement stmt = null;
        try{
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idpeople, namePeople, NameCity FROM people JOIN city ON (numberCity=idCity)";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                //int idCity  = rs.getInt("");
                String NamePeople = rs.getString("namePeople");
                String NameCity = rs.getString("NameCity");
                String idpeople = rs.getString("idpeople");

                //Display values
                System.out.print("IDPeople: " + idpeople + " ");
                //System.out.print(": " + gorodname);
                System.out.print("NamePeople: " + NamePeople+" ");
                System.out.println("NameCity : " + NameCity);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
        } catch(SQLException se2){
            System.out.println(se2);
        }// nothing we can do
    }
    private void doInsert(Connection conn) {
        Statement stmt = null;
        try {
            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO city (NameCity) VALUES ('Sumy')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");


        } catch(SQLException se2){
        }// nothing we can do
    }

    private void doUpdate(Connection conn) {
        Statement stmt = null;
        try {

            System.out.println("Updating records into the table...");
            stmt = conn.createStatement();

            String sql = "Update people set NumberCity=3 where (idpeople<3)";
            stmt.executeUpdate(sql);
            System.out.println("Updated records into the table...");
        } catch(SQLException se2){
        }// nothing we can do
    }

    private void doDelete (Connection conn) {
        Statement stmt = null;
        try {

            System.out.println("Deleting records into the table...");
            stmt = conn.createStatement();

            String sql = "Delete from City where (idCity=1)";
            stmt.executeUpdate(sql);
            System.out.println("Deleted records into the table...");


        } catch(SQLException se2){
        }// nothing we can do
    }
    private void doCreateTable (Connection conn) {
        Statement stmt = null;
        try {

            System.out.println("Creating the table...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE Age " +
                    "(idAge INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                    "number INT not null);";
            stmt.executeUpdate(sql);
            System.out.println("Created the table...");
        } catch(SQLException se2){
            System.out.println(se2);
        }// nothing we can do
    }
    private void doAlterTable (Connection conn) {
        Statement stmt = null;
        try {

            System.out.println("Altering the table...");
            stmt = conn.createStatement();

            String sql = "AlTER TABLE java.AgePeople add  FOREIGN KEY (idPeople) REFERENCES city(idCity);";
            stmt.executeUpdate(sql);
            System.out.println("Altered the table...");
        } catch(SQLException se2){
            System.out.println(se2);
        }// nothing we can do
    }

    private void close(Connection conn){
        try {
            //STEP 6: Clean-up environment
            if(conn!= null) {
                conn.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //STEP 4: Execute a query
        //finally block used to close resources
        Lab1 fp=new Lab1();
        Connection c=fp.connect();
       // fp.doInsert(c);
        //fp.doUpdate(c);
        //fp.doDelete(c);
        fp.doAlterTable(c);
        fp.close(c);
        System.out.println("Goodbye!");
    }//end main
}
