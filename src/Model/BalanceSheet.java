package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jengel1 on 10/17/2018.
 */
public class BalanceSheet {

    /*
    Variables for constructor
     */
    //name of balanceSheet
    String balName;
    //balance sheet date range
    String balStart;
    String balEnd;
    //income
    Map<String, Double> income = new HashMap<>();
    //expenses
    Map<String, Double> expenses = new HashMap<>();

    //constructor
    public BalanceSheet (String balName, String balStart, String balEnd, Map<String, Double> income, Map<String, Double> expenses){
        this.balName = balName;
        this.balStart = balStart;
        this.balEnd = balEnd;
        this.income = income;
        this.expenses = expenses;
    }

    //getters and setters
    public String getBalName(){return balName;}
    public void setBalName(String newBalName){balName = newBalName;}
    public String getBalStart(){return balStart;}
    public void setBalStart(String newBalStart){balStart = newBalStart;}
    public String getBalEnd(){return balEnd;}
    public void setBalEnd(String newBalEnd){balEnd = newBalEnd;}

    /*
    Methods to handle Map functionality
     */
    //Returns a string listing the items in the Map parameter
    public String showItems(Map<String, Double> map){
        String line = "";
        for(String k : map.keySet()){
//            System.out.println(k + ": " + expenses.get(k));
            line += k + ": " + map.get(k) + "\n";
        }
        return line;
    }
    //get sum of all items in the Map parameter
    private double getSum(Map<String, Double> map){
        double sum = 0;
        for(String k : map.keySet()){
            sum = sum + map.get(k);
        }
        return sum;
    }

//    //Adds an expense to the expenses dictionary
//    public void addExpense(String key, double value){
//        expenses.put(key, value);
//    }
//    //Modifies the value of an expense
//    public void modExpense(String key, double value){
//        expenses.replace(key, value);
//    }
//    //Deletes an expense
//    public void delExpense(String key){
//        expenses.remove(key);
//    }

    /*
    Method to format how Balance Sheet data is returned
     */
    public String getBalanceSheet(){
        double totalIncome = getSum(income);
        double totalExpenses = getSum(expenses);
        return balName + "\n" +
                "From: " + balStart + " - " + balEnd + "\n" +
                "List of Income:\n" +
                showItems(income) +
                "Total Income: " + totalIncome + "\n" +
                "List of Expenses:\n" +
                showItems(expenses) +
                "Total Expenses: " + totalExpenses + "\n" +
                "Net Income: " + (totalIncome + totalExpenses);  //total expenses is a negative number
    }


}
