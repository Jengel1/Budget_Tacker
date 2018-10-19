import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jengel1 on 10/17/2018.
 */
public class BalanceSheet {

    //name of balanceSheet
    String balName;
    //income
    double income;
    //expenses
    Map<String, Double> expenses = new HashMap<>();
//    //net income
//    double netIncome;
    //balance sheet duration
    LocalDate balStart;
    LocalDate balEnd;

    //constructor
    public BalanceSheet (String balName, double income, Map<String, Double> expenses, LocalDate balStart, LocalDate balEnd){
        this.balName = balName;
        this.income = income;
        this.expenses = expenses;
//        this.netIncome = netIncome;
        this.balStart = balStart;
        this.balEnd = balEnd;
    }

    //getters and setters
    public String getBalName(){return balName;}
    public void setBalName(String newBalName){balName = newBalName;}

    public double getIncome(){return income;}
    public void setIncome(long newIncome){income = newIncome;}

    public LocalDate getBalStart(){return balStart;}
    public void setBalStart(LocalDate newBalStart){balStart = newBalStart;}
    public LocalDate getBalEnd(){return balEnd;}
    public void setBalEnd(LocalDate newBalEnd){balEnd = newBalEnd;}


    /*
    Methods to calculate net income
     */
    //calculate income minus expenses
    public double getNetIncome(){
        return income - expenseSum();
    }
    //get sum of all expenses
    private double expenseSum(){
        double sum = 0;
        for(String k : expenses.keySet()){
            sum = sum + expenses.get(k);
        }
        return sum;
    }


    /*
    Methods to handle Map functionality
     */
    //Prints out a list of current expenses
    public String showExpenses(){
        String line = "";
        for(String k : expenses.keySet()){
//            System.out.println(k + ": " + expenses.get(k));
            line += k + ": " + expenses.get(k) + "\n";
        }
        return line;
    }
    //Adds an expense to the expenses dictionary
    public void addExpense(String key, double value){
        expenses.put(key, value);
    }
    //Modifies the value of an expense
    public void modExpense(String key, double value){
        expenses.replace(key, value);
    }
    //Deletes an expense
    public void delExpense(String key){
        expenses.remove(key);
    }

    /*
    Method to format how Balance Sheet data is printed
     */
    public void printBalanceSheet(){
        System.out.println(balName + "\n" +
            "Income: " + income + "\n" +
            "List of Expenses:\n" +
            showExpenses() +
            "Total Expenses: " + expenseSum() + "\n" +
            "Net Income: " + getNetIncome());
    }


}
