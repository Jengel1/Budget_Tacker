package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jengel1 on 9/25/2018.
 */
public class DBManager {

    /*
    CSV Files containing all financial info
     */
    static String filePath = "C:\\Users\\Jerali\\Documents\\Github\\budget_tracker\\BudgetTrackerTestData\\";  //main file path
    static String csvAspFile = filePath + "Aspiration.csv";
    static String csvCap1File = filePath + "CapitalOne.csv";
    static String csvBarFile = filePath + "Barclay.csv";

    /*
    Bank Transactions Table
     */
    //Constant for identifying bank transactions table
    public static final String TABLE_BANK_TRANSACTIONS = "bank_transactions";
    //Constants for identifying bank transactions columns
    public static final String BANK_DESCRIPTION = "description";
    public static final String BANK_CATEGORY = "category";
    public static final String BANK_AMOUNT = "amount";
    public static final String BANK_BALANCE = "balance";
    public static final String BANK_DATE = "date";
    //Constant for retrieving bank transactions data
    public static final String ALL_BANK_TRANSACTIONS_COLUMNS = BANK_DESCRIPTION + ", " + BANK_CATEGORY + ", " +
            BANK_AMOUNT + ", " + BANK_BALANCE + ", " + BANK_DATE;
    //SQL statement to create bank transactions table
    public static final String BANK_TRANSACTIONS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BANK_TRANSACTIONS + " (" +
                    BANK_DESCRIPTION + " TEXT NOT NULL, " +
                    BANK_CATEGORY + " TEXT, " +
                    BANK_AMOUNT + " DECIMAL(10, 5) NOT NULL, " +
                    BANK_BALANCE + " DECIMAL(10, 5) NOT NULL, " +
                    BANK_DATE + " TEXT NOT NULL" +
                    ")";
    //SQL statement to insert values into bank transactions table
    public static final String BANK_TRANSACTIONS_TABLE_INSERT =
            "INSERT INTO " + TABLE_BANK_TRANSACTIONS + " (" + ALL_BANK_TRANSACTIONS_COLUMNS + ") VALUES " +
                    CSV_ETL.loadValuesToDB(CSV_ETL.transformAspiration(CSV_ETL.extractCSVFile(csvAspFile)));  //use Model.CSV_ETL methods to retrieve values from files
    //SQL statement to update category value in bank transactions table
    static String bank_userDefinedValue = "";
    public static final String BANK_TRANSACTIONS_TABLE_UPDATE =
            "UPDATE " + TABLE_BANK_TRANSACTIONS + " SET " + BANK_CATEGORY + " = '" + bank_userDefinedValue + "'" +
            " WHERE conditionTBD";

    /*
    Credit Card Transactions Table
     */
    //Constant for identifying credit card transactions table
    public static final String TABLE_CC_TRANSACTIONS = "cc_transactions";
    //Constants for identifying transactions columns
    public static final String CC_DESCRIPTION = "description";
    public static final String CC_CATEGORY = "category";
    public static final String CC_AMOUNT = "amount";
    public static final String CC_DATE = "date";
    //Constant for retrieving credit card transactions data
    public static final String ALL_CC_TRANSACTIONS_COLUMNS = CC_DESCRIPTION + ", " + CC_CATEGORY + ", " +
            CC_AMOUNT + ", " + CC_DATE;
    //SQL statement to create credit card transactions table
    public static final String CC_TRANSACTIONS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CC_TRANSACTIONS + " (" +
                    CC_DESCRIPTION + " TEXT NOT NULL, " +
                    CC_CATEGORY + " TEXT, " +
                    CC_AMOUNT + " DECIMAL(10, 5) NOT NULL, " +
                    CC_DATE + " TEXT NOT NULL" +
                    ")";
    //SQL statement to insert values into credit card transactions table
    public static final String CC_TRANSACTIONS_TABLE_INSERT =
            "INSERT INTO " + TABLE_CC_TRANSACTIONS + " (" + ALL_CC_TRANSACTIONS_COLUMNS + ") VALUES " +
                    CSV_ETL.loadValuesToDB(CSV_ETL.transformCCs(CSV_ETL.extractCSVFile(csvCap1File), CSV_ETL.extractCSVFile(csvBarFile)));  //use Model.CSV_ETL methods to retrieve values from files
    //SQL statement to update category value in credit card transactions table
    static String cc_userDefinedValue = "";
    public static final String CC_TRANSACTIONS_TABLE_UPDATE =
            "UPDATE " + TABLE_CC_TRANSACTIONS + " SET " + CC_CATEGORY + " = '" + cc_userDefinedValue + "'" +
                    " WHERE conditionTBD";

    //create bank transactions table
    public static void createBankTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute(BANK_TRANSACTIONS_TABLE_CREATE);
        System.out.println(BANK_TRANSACTIONS_TABLE_CREATE);
        conn.close();
    }

    //create cc transactions table
    public static void createCCTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute(CC_TRANSACTIONS_TABLE_CREATE);
        System.out.println(CC_TRANSACTIONS_TABLE_CREATE);
        conn.close();
    }

    //insert into bank table
    public static void insertIntoBankTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        System.out.println(BANK_TRANSACTIONS_TABLE_INSERT);
        stmt.execute(BANK_TRANSACTIONS_TABLE_INSERT);
        conn.close();
    }

    //insert into cc table
    public static void insertIntoCCTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        System.out.println(CC_TRANSACTIONS_TABLE_INSERT);
        stmt.execute(CC_TRANSACTIONS_TABLE_INSERT);
        conn.close();
    }

    //update bank table
    public static void updateBankTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute(BANK_TRANSACTIONS_TABLE_UPDATE);
        System.out.println(BANK_TRANSACTIONS_TABLE_UPDATE);
        conn.close();
    }

    //update cc table
    public static void updateCCTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute(CC_TRANSACTIONS_TABLE_UPDATE);
        System.out.println(CC_TRANSACTIONS_TABLE_UPDATE);
        conn.close();
    }


    //select statement from bank table
    public static void getBankValues() throws SQLException {
        String selectStmt = "SELECT * FROM " + TABLE_BANK_TRANSACTIONS;
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
            String bal = Double.toString(rs.getDouble(4));
            String date = rs.getString(5);
            ArrayList<String> values = new ArrayList<>(Arrays.asList(desc, catg, amt, bal, date));
            myArr.add(values);
        }
        System.out.println(myArr.toString());
        conn.close();
    }

    //select statement from cc table
    public static void getCCValues() throws SQLException {
        String selectStmt = "SELECT * FROM " + TABLE_CC_TRANSACTIONS;
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


    //drop bank table from DB
    public static void dropBankTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE " + TABLE_BANK_TRANSACTIONS);
        System.out.println("table dropped");
        conn.close();
    }

    //drop cc table from DB
    public static void dropCCTable() throws SQLException {
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE " + TABLE_CC_TRANSACTIONS);
        System.out.println("table dropped");
        conn.close();
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
