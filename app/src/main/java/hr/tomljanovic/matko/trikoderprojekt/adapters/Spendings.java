package hr.tomljanovic.matko.trikoderprojekt.adapters;

public class Spendings {

    public String spender;  // Data which will be send to the list
    public String amount;
    public int idUser;

    public Spendings(String spender, String amount, int idUser) {
        this.spender = spender;
        this.amount = amount;
        this.idUser = idUser;
    }
}
