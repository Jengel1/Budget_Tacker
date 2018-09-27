import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jengel1 on 9/25/2018.
 */
public class BudgetTracker {

    public static void main(String[] args){
        System.out.println("Hello World!");

        double ranNum = Math.round(Math.random() * 4);
        if (ranNum == 0){
            ranNum++;
        }
        System.out.println(ranNum);


//        try {
////            DBManager.createTable();
//            DBManager.insertIntoTable();
//            DBManager.getValues();
////            DBManager.dropTable();
//        } catch (SQLException e){
//            System.out.println(e.getMessage());
//        }

    }
}
