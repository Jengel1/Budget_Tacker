import Model.BalanceSheet;
import View.MainScrn;
import javafx.application.Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jengel1 on 9/25/2018.
 */
public class BudgetTracker {

    public static void main(String[] args){
//        System.out.println("Hello World!");
        Application.launch(MainScrn.class, args);




//        try {
//            System.out.println(makeBalanceSheet("September Balance Sheet", "9/1/2018", "9/30/2018"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//        String path = "C:\\Users\\Jerali\\Documents\\Github\\budget_tracker\\BudgetTrackerTestData\\";
//        String csvAspFile = path + "Aspiration.csv";
//        String csvCap1File = path + "CapitalOne.csv";
//        String csvBarFile = path + "Barclay.csv";
//        System.out.println("Bank Values:");
////        CSV_ETL.transformAspiration(CSV_ETL.extractCSVFile(csvAspFile));
//        System.out.println(CSV_ETL.loadValuesToDB(CSV_ETL.transformAspiration(CSV_ETL.extractCSVFile(csvAspFile))));
//        System.out.println();
//        System.out.println("CC Values");
////        CSV_ETL.transformCCs(CSV_ETL.extractCSVFile(csvCap1File), CSV_ETL.extractCSVFile(csvBarFile));
//        System.out.println(CSV_ETL.loadValuesToDB(CSV_ETL.transformCCs(CSV_ETL.extractCSVFile(csvCap1File), CSV_ETL.extractCSVFile(csvBarFile))));

//        try {
//            DBManager.createBankTable();
//            DBManager.createCCTable();
//            DBManager.insertIntoBankTable();
//            DBManager.insertIntoCCTable();
//            DBManager.getBankValues();
//            DBManager.getCCValues();
////            DBManager.dropBankTable();
////            DBManager.dropCCTable();
//        } catch (SQLException e){
//            System.out.println(e.getMessage());
//        }



//        Map<String, Double> expenses = new HashMap<>();
//        expenses.put("Rent", 1403.00);
//        expenses.put("Parking", 180.00);
//        expenses.put("Credit Card", 1200.00);
//
//        Model.BalanceSheet novBalSht = new Model.BalanceSheet("November Balance Sheet", 4200, expenses,
//                LocalDate.of(2018,11,1), LocalDate.of(2018,11,30));
//
//        novBalSht.printBalanceSheet();



//        for(String k : expenses.keySet()){
//            System.out.println(k + ": " + expenses.get(k));
//        }
//
//        expenses.replace("Credit Card", 1300.00);
//
//        for(String k : expenses.keySet()){
//            System.out.println(k + ": " + expenses.get(k));
//        }
//
//        expenses.remove("Credit Card");
//
//        for(String k : expenses.keySet()){
//            System.out.println(k + ": " + expenses.get(k));
//        }




//        try {
//            System.out.println(getBankBalance("9/14/2018"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            whenIsPay("2018-11-02");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }




//        double ranNum = Math.round(Math.random() * 4);
//        if (ranNum == 0){
//            ranNum++;
//        }
//        System.out.println(ranNum);

    }

//    private Stage primaryStage;
//    /*
//    Method to start GUI by creating primary stage
//     */
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Budget Tracker");
//    }


    /*
    Method prints out the date of each paycheck for 1 year
    Indicates when there are 3 paychecks in a single month
    @param myDate the string passed into the method representing the start date of the first pay check
     */
    private static void whenIsPay(String startDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  // formatter
        LocalDate firstPayDay = formatter.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();  //convert string to Localdate
        /*
        Make endDate user defined
         */
        LocalDate endDate = firstPayDay.plusYears(1);  //establish endDate
        int month = firstPayDay.getMonthValue();  //variable to hold month integer value
        int paycheckCounter = 0;  //variable to track number of paychecks
        for(LocalDate date = firstPayDay; date.isBefore(endDate); date = date.plusWeeks(2)){  //create for loop
            System.out.println(date);  //print out date of paycheck
            if(month == date.getMonthValue()){  //if paycheck in same month as previous paycheck
                paycheckCounter++;  //increment number of paychecks
                if(paycheckCounter == 3){  //if 3 paychecks in a single month
                    System.out.println("You get 3 paychecks in the month of " + date.getMonth());
                }
            }
            if(month != date.getMonthValue()){  //if paycheck in different month than previous paycheck
                paycheckCounter = 1;  //number of paychecks equal 1
                month = date.getMonthValue();  //set variable holding month integer value to new month integer value
            }
        }
    }

    /*
    Method to return current bank balance
    Assumption -- if date contains multiple entries, most current entry returned first
    @param date the date assigned to the balance query
     */
    private static double getBankBalance(String date) throws SQLException {
        System.out.println(date);
        String selectStmt = "SELECT balance FROM " + DBManager.TABLE_BANK_TRANSACTIONS +
                " WHERE date = '" + date + "'";
        System.out.println(selectStmt);

        ArrayList<Double> myArr = new ArrayList<>();  //create list in case of multiple entries on the same date

        Connection conn = DBManager.connectToDB();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(selectStmt);

        while (rs.next()){
            double result = rs.getDouble(1);
            myArr.add(result);
        }
        conn.close();
        return myArr.get(0);  //return the first entry as it is assumed to be the most current
    }


    /*
    Method to pull data from DB to create a balance sheet given a user defined time frame
     */
    private static String makeBalanceSheet(String balName, String startDate, String endDate) throws SQLException {
        //income select query
        String stmtGetIn = "SELECT description, amount FROM bank_transactions " +
                "WHERE (date > '" + startDate + "' OR date < '" + endDate + "') AND amount > 0";
        System.out.println(stmtGetIn);
        //expense select query
        String stmtGetEx = "SELECT description, amount FROM bank_transactions " +
                "WHERE (date > '" + startDate + "' OR date < '" + endDate + "') AND amount < 0;";
        System.out.println(stmtGetEx);

        Connection conn = DBManager.connectToDB();
        Statement stmtIn = conn.createStatement();
        Statement stmtEx = conn.createStatement();
        ResultSet rsGetIn = stmtIn.executeQuery(stmtGetIn);
        ResultSet rsGetEx = stmtEx.executeQuery(stmtGetEx);

        Map<String, Double> income = new HashMap<>();
        Map<String, Double> expenses = new HashMap<>();

        while(rsGetIn.next()){
            income.put(rsGetIn.getString(1), rsGetIn.getDouble(2));
        }
        while(rsGetEx.next()){
            expenses.put(rsGetEx.getString(1), rsGetEx.getDouble(2));
        }

        BalanceSheet septBalSht = new BalanceSheet(balName, startDate, endDate, income, expenses);
        conn.close();
        return septBalSht.getBalanceSheet();
    }

}
