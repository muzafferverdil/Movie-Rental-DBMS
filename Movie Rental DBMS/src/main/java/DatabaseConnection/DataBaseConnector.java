package DatabaseConnection;

import DatabaseConnection.Entities.CompareByRelDate;
import DatabaseConnection.Entities.Movie;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DataBaseConnector {

    private static ArrayList movies = new ArrayList<>();

    public static ArrayList<Movie> getMovies(){
        newReleasesMovies();
        return movies;
    }

    public static void newReleasesMovies(){

        try {
            Statement statement = createConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT title,imdb,releaseDate,picture,movieId FROM movierental.movie");
            while (rs.next()){
                System.out.println("k");
                String title = rs.getString("title");
                double imdb = rs.getDouble("imdb");
                Date date = rs.getDate("releaseDate");
                String picture = rs.getString("picture");
                int id = rs.getInt("movieId");
                movies.add(new Movie(title,imdb,date,picture,id));
                System.out.println("movies size : "+movies.size());
                System.out.println(title+" "+imdb+" "+date+" "+picture);
            }
            statement.close();

            Collections.sort(movies,new CompareByRelDate());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Object> getInfoList(String tableName,String columnName, String condition){

        ArrayList<Object> list = new ArrayList<>();
        try {
            Statement statement = createConnection().createStatement();
            String query = "SELECT "+columnName+" FROM "+"movierental."+tableName+" WHERE "+condition;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
               list.add(rs.getObject(columnName));
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static Object getInfo(String tableName,String columnName, String condition){
        Object obj = new Object();
        try {
            Statement statement = createConnection().createStatement();
            String query = "SELECT "+columnName+" FROM "+"movierental."+tableName+" WHERE "+condition;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                obj = rs.getObject(columnName);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static boolean isthereDB(){
        try {
            Statement statement = createConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'movieRental'");
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public static boolean insertInfo(String tableName,String colmns,String value){
        try {
            Statement statement = createConnection().createStatement();
            String query = "INSERT INTO "+"movierental."+tableName+"("+colmns+")"+"VALUES"+"("+value+")";
            statement.execute(query);
            statement.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Insert operation is failed..");
            return false;
        }
    }

    public static void updateInfo(String tableName,String value,String condition){// UPDATE `movierental`.`customer` SET `Adress` = 'yt' WHERE (`personId` = '34');
        try {
            Statement statement = createConnection().createStatement();
            String query = "UPDATE "+"movierental."+tableName+" SET "+value+" WHERE "+"("+condition+")";
            statement.execute(query);
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Update Operation is failed.");
        }
    }

    public static Boolean checkValidation(String tableName,String columnName, String condition){
        try {
            Statement statement = createConnection().createStatement();
            String query = "SELECT "+columnName+" FROM "+" movierental."+tableName+" WHERE "+condition;
            ResultSet rs = statement.executeQuery(query);
            if(!rs.next()){
                return true;
            }
            else {
                System.out.println("Email var");
                return false;
            }
        }catch (Exception e){
            System.out.println("burada hata var");
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static Integer max(String colName,String tableName){
        int max = 0;
        try {
            Statement statement = createConnection().createStatement();
            String query = "SELECT MAX("+colName+")"+" FROM "+" movierental."+tableName;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                max = rs.getInt("max("+colName+")");
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Max Id Error");
        }
        return max;
    }

    public static ArrayList<Object> search(String tableName,String columnName, String source,String search){//SELECT title FROM movie WHERE title LIKE '%moun%'
        ArrayList<Object> list = new ArrayList<>();
        try {
            Statement statement = createConnection().createStatement();
            String searchValue = "'%"+search+"%'";
            String query = "SELECT "+columnName+" FROM "+"movierental."+tableName+" WHERE "+source+" LIKE "+searchValue;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                list.add(rs.getObject(columnName));
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static boolean delete(String tableName, String condition){//DELETE FROM `movierental`.`person` WHERE (`personId` = '1');
        try {
            Statement statement = createConnection().createStatement();
            String query = "DELETE FROM movierental."+tableName+" WHERE ("+condition+")";
            System.out.println("Silindi");
            statement.execute(query);
            statement.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Delete operation is failed.");
            return false;
        }
    }

    public static Connection createConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");     // It starts the driver.
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC","root","mert33yyy");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
