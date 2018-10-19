import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
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
//        BalanceSheet novBalSht = new BalanceSheet("November Balance Sheet", 4200, expenses,
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
//            whenIsPay("2018-11-09");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }




//        double ranNum = Math.round(Math.random() * 4);
//        if (ranNum == 0){
//            ranNum++;
//        }
//        System.out.println(ranNum);

    }



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


}
