import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jengel1 on 9/25/2018.
 */
public class DBManager {

    /*
    Transactions Table
     */
    //Constant for identifying transactions table
    public static final String TABLE_TRANSACTIONS = "transactions";
    //Constants for identifying transactions columns
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY = "category";
    public static final String AMOUNT = "amount";
    public static final String DATE = "date";
    //Constant for retrieving transactions data
    public static final String ALL_TRANSACTIONS_COLUMNS = DESCRIPTION + ", " + CATEGORY + ", " +
            AMOUNT + ", " + DATE;
    //SQL statement to create transactions table
    public static final String TRANSACTIONS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTIONS + " (" +
                    DESCRIPTION + " TEXT NOT NULL, " +
                    CATEGORY + " TEXT, " +
                    AMOUNT + " DECIMAL(10, 5) NOT NULL, " +
                    DATE + " TEXT NOT NULL" +
                    ")";
    //SQL statement to insert values into transactions table
    public static final String TRANSACTIONS_TABLE_INSERT =
            "INSERT INTO " + TABLE_TRANSACTIONS + " (" + ALL_TRANSACTIONS_COLUMNS + ") VALUES " +
                    "('rent', 'housing', " + 1500.70 + ", '10/14/2018')";

    //create DB
    public static void createTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute(TRANSACTIONS_TABLE_CREATE);
        System.out.println(TRANSACTIONS_TABLE_CREATE);
        conn.close();
    }

    //insert into DB
    public static void insertIntoTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute(TRANSACTIONS_TABLE_INSERT);
        System.out.println(TRANSACTIONS_TABLE_INSERT);
        conn.close();
    }

    //select statement
    public static void getValues() throws SQLException {
        String selectStmt = "SELECT * FROM " + TABLE_TRANSACTIONS;
        System.out.println(selectStmt);

        ArrayList<ArrayList<String>> myArr = new ArrayList<>();

        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
//        System.out.println(stmt.execute(selectStmt));
        ResultSet rs = stmt.executeQuery(selectStmt);
        while (rs.next()){
            String desc = rs.getString(1);
            String catg = rs.getString(2);
            String amt = Double.toString(rs.getDouble(3));
            String date = rs.getString(4);
            ArrayList<String> values = new ArrayList<>(Arrays.asList(desc, catg, amt, date));
            myArr.add(values);
        }

        System.out.println(myArr.toString());
        conn.close();
    }

    public static void dropTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE " + TABLE_TRANSACTIONS);
        System.out.println("table dropped");
    }

    /*
    Method to connect to DB
     */
    public static Connection connectToDB() throws SQLException {
        //DB parameters
        String url = "jdbc:sqlite:C:\\Users\\Jerali\\Documents\\Github\\budget_tracker\\budget_tracker.db";
        //Establish connection
        Connection conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite DB successful");
        return conn;
    }

}
