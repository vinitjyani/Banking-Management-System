import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;

    public User(Connection connection,Scanner scanner){
        this.scanner=scanner;
        this.connection=connection;
    }

    public void register(){
        scanner.nextLine();
        System.out.println("Full Name: ");
        String full_name = scanner.nextLine();
        System.out.println("Email :");
        String email = scanner.nextLine();
        System.out.println("Password :");
        String password = scanner.nextLine();
        if(userexist(email)){
            System.out.println("User already exists for the Email address");
            return;
        }
        String register_query = "INSERT INTO User(full_name,email,password) values(?,?,?);";
        try{
            PreparedStatement ps = connection.prepareStatement(register_query);
            ps.setString(1,full_name);
            ps.setString(2,email);
            ps.setString(3,password);
            int affectedrows = ps.executeUpdate();
            if(affectedrows>0){
                System.out.println("Registeration Successfull");
            }else{
                System.out.println("Registeration failed");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public String login(){
        scanner.nextLine();
        System.out.println("Email : ");
        String email = scanner.nextLine();
        System.out.println("Password :");
        String password  = scanner.nextLine();
        String login_query = "SELECT * FROM User WHERE email=? AND pssword =?;";
        try{
            PreparedStatement ps = connection.prepareStatement(login_query);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return email;
            }
            else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean userexist(String email){
        String query = "SELECT * FROM User WHERE email = ?;";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
