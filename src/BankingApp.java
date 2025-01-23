import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class BankingApp{

    private static final String url ="";
    private static final String username = "root";
    private static final String password = "vinit17042002";

    public static void main(String[] args) {

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");

    } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());
    }
    try{
        Connection connection = DriverManager.getConnection(url,username,password);
        Scanner scanner = new Scanner(System.in);
        User user  = new User(connection,scanner);
        Accounts accounts = new Accounts(connection,scanner);
        AccountManager accountManager = new AccountManager(connection,scanner);
    }
    catch (){

    }
    }
}