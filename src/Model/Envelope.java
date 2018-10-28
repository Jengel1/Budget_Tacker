package Model;

/**
 * Created by Jengel1 on 10/27/2018.
 */
public class Envelope {

    private String name;
    private double amount;

    public Envelope(String name, double amount){
        this.name = name;
        this.amount = amount;
    }

    public String getName(){return name;}
    public void setName(String newName){newName = name;}

    public double getAmount(){return amount;}
    public void setAmount(double newAmount){newAmount = amount;}
}
