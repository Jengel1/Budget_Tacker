import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jengel1 on 10/18/2018.
 */
public class CSV_ETL {


    //ETL extract method for csv data
    //aspiration and capital one have single header line
    //eventually add logic to handle barclay multi-line header, for now manually delete in csv file before extraction
    public static ArrayList<String[]> extractCSVFile(String file){
        ArrayList<String[]> listOfValues = new ArrayList<>();

        String csvFile = file;
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();  //reads headerline first so it is not involved in while loop
            while((line = br.readLine()) != null){
                String[] transaction = line.split(csvSplitBy);
//                System.out.println(transaction[0] + ", " + transaction[1] + ", " + transaction[2] + ", " + transaction[3] + ", " +
//                        transaction[4] + ", " + transaction[5]);
                listOfValues.add(transaction);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return listOfValues;
    }

    //ETL transform method for aspiration csv
    //Aspiration current order: date, description, amount, running balance
    //order needed: description, category, amount, date
    public static ArrayList<String> transformAspiration(ArrayList<String[]> list){
        ArrayList<String> transformedListOfValues = new ArrayList<>();
        String newTransaction = "";
        for(String[] s : list){
            newTransaction = "'" + s[1] + "', '', " + s[2] + ", '" + s[0] + "'";
//            System.out.println(newTransaction);
            transformedListOfValues.add(newTransaction);
        }
        return transformedListOfValues;
    }

    //ETL transform method for Capital One csv & Barclay csv
    //Capital One current order: transaction date, posted date, card no., description, category, debit, credit
    //Barclay current order: date, description, category(debit, credit), amount
    //order needed: description, category, amount, date
    public static ArrayList<String> transformCCs(ArrayList<String[]> c1List, ArrayList<String[]> bList){
        ArrayList<String> transformedListOfValues = new ArrayList<>();
        String newTransaction = "";
        for(String[] s : c1List){  //for the capital one values
            if(s[4].equals("Payment/Credit")){
                newTransaction = "'" + s[3] + "', '" + s[4] + "', -" + s[6] + ", '" + s[0] + "'"; //turn s[6] into a negative
//                System.out.println(newTransaction);
                transformedListOfValues.add(newTransaction);
            } else {
                newTransaction = "'" + s[3] + "', '" + s[4] + "', " + s[5] + ", '" + s[0] + "'";
//                System.out.println(newTransaction);
                transformedListOfValues.add(newTransaction);
            }
        }
        for(String[] s : bList){  //for the barclay values
            if(s[2].equals("CREDIT")){
                newTransaction = "'" + s[1] + "', '', -" + s[3] + ", '" + s[0] + "'";  //turn s[3] into a negative
//                System.out.println(newTransaction);
                transformedListOfValues.add(newTransaction);
            } else {
                String posAmount = s[3].substring(1);  //strip negative (-) sign from string to make it positive
                newTransaction = "'" + s[1] + "', '', " + posAmount + ", '" + s[0] + "'";
//                System.out.println(newTransaction);
                transformedListOfValues.add(newTransaction);
            }
        }
        return transformedListOfValues;
    }

    //Method to load values into DB
    public static String loadValuesToDB(ArrayList<String> list){
        String loadString = "";
        for(int i = 0; i < list.size(); i++){
            if(i == (list.size() -1)){
                loadString += "(" + list.get(i) + ")";
            } else {
                loadString += "(" + list.get(i) + "), \n";
            }
        }
        return loadString;
    }


}
