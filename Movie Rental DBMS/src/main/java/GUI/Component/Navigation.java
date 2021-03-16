package GUI.Component;

import DatabaseConnection.DataBaseConnector;
import GUI.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Navigation {

        private static HBox hBox = new HBox();
        private static HBox hBox2 = new HBox(15);
        private static Label home = new Label("Home");
        private static Button button = new Button("Search");
        private static TextField textField = new TextField("Search Customer");


    public static VBox navigation(){
        VBox vBox = new VBox();

        hBox.setId("header1");
        Label signIn = new Label("Sign In");
        Label signUp = new Label("Sign Up");
        textField.setFocusTraversable(false);

        if(!SignInWindow.on) {
            hBox.getChildren().clear();
            hBox.getChildren().add(new Label("Your Movies At Home"));
            hBox2.getChildren().clear();
            hBox2.setId("sectionHbox");
            hBox2.getChildren().addAll(home,signIn,signUp);
        }


        vBox.getChildren().addAll(hBox,hBox2);

        signUp.setOnMouseClicked(e->{
            SignUpWindow signUpWindow = new SignUpWindow();
            signUpWindow.paint();
        });
        signIn.setOnMouseClicked(e->{
            SignInWindow signInWindow = new SignInWindow();
            signInWindow.paint();
        });
        home.setOnMouseClicked(e->{
            MainWindow mainWindow = new MainWindow();
            Launcher.changeScene(mainWindow.paint());
        });

        button.setOnAction(e->{
                CustomerOpWindow customerOpWindow = new CustomerOpWindow();
                ArrayList<Object> listCustomer = new ArrayList<>();
                ArrayList<Object> list = searchAction("person","personId","name",textField.getText());

                for(int i=0; i<list.size(); i++){
                    boolean exist = DataBaseConnector.checkValidation("customer","personId","personId="+list.get(i));
                    if(!exist){
                        System.out.println(list.get(i));
                        listCustomer.add(list.get(i));
                    }
                }
                customerOpWindow.customers = listCustomer;
                customerOpWindow.paint();

        });

        return vBox;
    }

    public static void setUserName(String name){

        common(name);
        Label myMovies = new Label("My Movies");
        hBox2.getChildren().add(1,myMovies);

        myMovies.setOnMouseClicked(e->{
            MyMoviesWindow myMoviesWindow = new MyMoviesWindow();
            myMoviesWindow.paint();
        });
    }

    public static void setEmplayee(String name){
        Label label = new Label("Operations");
        label.setOnMouseClicked(e->{
            EmployeeWindow employeeWindow = new EmployeeWindow();
            employeeWindow.paint();
            Navigation.setEmplayee(name);
        });
        common(name);
        hBox2.getChildren().addAll(textField,button);
        hBox2.getChildren().add(1,label);
    }

    public static void setManger(String name){
        Label label = new Label("Operations");
        label.setOnMouseClicked(e->{
            ManagerWindow managerWindow = new ManagerWindow();
            managerWindow.paint();
            Navigation.setManger(name);
        });
        common(name);
        hBox2.getChildren().addAll(textField,button);
        hBox2.getChildren().add(1,label);
    }

    public static void common(String name){
        Label label = new Label("Welcome "+name);
        hBox.getChildren().clear();
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(new Label("Your Movies At Home"),label);
        hBox.getChildren().addAll(vBox);
        hBox2.getChildren().clear();
        Label logOut = new Label("Log Out");
        hBox2.getChildren().addAll(home,logOut);

        logOut.setOnMouseClicked(e->{
            SignInWindow.on = false;
            MainWindow mainWindow = new MainWindow();
            Launcher.changeScene(mainWindow.paint());
        });
    }

    public static ArrayList<Object> searchAction(String tableName, String colName, String source,String search){
        ArrayList<Object> list = DataBaseConnector.search(tableName,colName,source,search);
        return list;
    }
}
