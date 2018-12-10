package General;

import com.mysql.jdbc.EscapeTokenizer;
import com.mysql.jdbc.PreparedStatement;
import sun.reflect.annotation.ExceptionProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Driver;

public class DBManager {
    private static Connection DBConnection = null;

    public static Boolean Close(){
        try {
            DBConnection.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static String[] GetNote(String URL)
    {
        PreparedStatement selectStatement;
        String query = null;
        String[] res;
        try{
            query = "SELECT * FROM notes WHERE url=?";
            selectStatement = (com.mysql.jdbc.PreparedStatement) DBConnection.prepareStatement(query);
            selectStatement.setString(1,URL);
            ResultSet result = selectStatement.executeQuery();
            while(result.next()) {
                    res= new String[5];
                    res[0]=result.getString("title");
                    res[1]= result.getString("content");
                    res[2]= result.getString("password");
                    res[3]=result.getString("token");
                    res[4]=result.getString("views");

                    return res;
            }
            selectStatement.close();

        }
        catch(SQLException e) {
            System.out.println("ERROR at UserExists:" + e.getMessage());
            return null;
        }
        return null;
    }


    public static boolean InsertNote(String title,String note, String URL, String password, String token)
    {
        try{
            PreparedStatement insertNote=null;

            String insertQuery="INSERT INTO notes (title,content,url,password,token) values (?,?,?,?,?)";

            insertNote = (com.mysql.jdbc.PreparedStatement) DBConnection.prepareStatement(insertQuery);
            insertNote.setString(1,title);
            insertNote.setString(2,note);
            insertNote.setString(3,URL);
            insertNote.setString(4,password);
            insertNote.setString(5,token);
            insertNote.executeUpdate();

            DBConnection.commit();

            insertNote.close();

            return true;
        }
        catch(SQLException e) {
            System.out.println("ERROR at InsertAccount:" + e.getMessage());
            return false;
        }
    }

    public static boolean UpdateNote(String token, String newTitle, String newContent)
    {
        try{
            PreparedStatement updateNote;
            String updateQuery = "UPDATE notes SET title=?,content =? WHERE token = ?";
            updateNote = (com.mysql.jdbc.PreparedStatement) DBConnection.prepareStatement(updateQuery);
            updateNote.setString(1,newTitle);
            updateNote.setString(2,newContent);
            updateNote.setString(3,token);
            updateNote.executeUpdate();
            DBConnection.commit();
            updateNote.close();
            return true;
        }catch(SQLException e){
            System.out.println("ERROR at UpdateNote" + e.getMessage());
            return false;
        }
    }

    public static boolean UrlExists(String URL){
        PreparedStatement selectStatement;
        String query = null;
        try{
            query = "SELECT * FROM notes WHERE url=?";
            selectStatement = (com.mysql.jdbc.PreparedStatement) DBConnection.prepareStatement(query);
            selectStatement.setString(1,URL);
            ResultSet result = selectStatement.executeQuery();
            while(result.next()) {
                if(!result.getString("url").isEmpty())
                    return true;
            }
            selectStatement.close();
            return false;
        }
        catch(SQLException e) {
            System.out.println("ERROR at UserExists:" + e.getMessage());
        }
        return false;
    }

    public static void OpenConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("NO");
        }
        try {
            DBConnection = DriverManager.getConnection("jdbc:mysql://localhost/noteit?useSSL=false&user=notesudo&password=parolaNoteIt20.");
            DBConnection.setAutoCommit(false);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
