package GUI;

import DatabaseConnection.DataBaseConnector;
import GUI.Component.Navigation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class SignInWindow {

    public static boolean on = false;
    public static int personId = 0;

    TextField email;
    PasswordField pswd;

    public SignInWindow() {


    }

    public void paint(){

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(section2());
        borderPane.setTop(Navigation.navigation());
        Scene scene = new Scene(borderPane,800,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }
    public GridPane section2(){

        GridPane gridPane = new GridPane();

        email = new TextField();
        email.setPromptText("example@gmail.com");
        pswd = new PasswordField();
        Button submit = new Button("Login");

        gridPane.add(new Label("Email :"),0,0);
        gridPane.add(email,1,0);
        gridPane.add(new Label("Password :"),0,1);
        gridPane.add(pswd,1,1);
        gridPane.add(submit,0,2);
        gridPane.setVgap(20);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        submit.setOnAction(e->{
            submitAction();
        });

        return gridPane;
    }

    public void submitAction() {

        boolean validateCustomer = DataBaseConnector.checkValidation("customer","personId","(email,password) = "+"('"+email.getText()+"','"+pswd.getText()+"')");

        boolean validateManager = DataBaseConnector.checkValidation("employee","personId","(email,password,managerId) = "+"('"+email.getText()+"','"+pswd.getText()+"','null'");

        boolean validateEmployee = DataBaseConnector.checkValidation("employee","personId","(email,password) = "+"('"+email.getText()+"','"+pswd.getText()+"')");


        if(!validateCustomer){
            afterValidate("customer");
        }
        else if(!validateManager){
          afterValidate("employee");
        }
        else if(!validateEmployee){
            afterValidate("employee");
        }
        else {
            JOptionPane.showMessageDialog(null,"No such account found.");
        }

    }

    public void afterValidate(String tableName){
        on = true;
        personId  = (Integer) DataBaseConnector.getInfo(tableName,"personId","email="+"'"+email.getText()+"'");
        email.clear();
        pswd.clear();
        MainWindow mainWindow = new MainWindow();
        Launcher.changeScene(mainWindow.paint());
        userType(personId);
    }

    public void userType(int personId){
        boolean isEmployee = DataBaseConnector.checkValidation("employee","personId","personId="+personId);
        boolean isCustomer = DataBaseConnector.checkValidation("customer","personId","personId="+personId);
        if(!isEmployee){
            int id = (Integer) DataBaseConnector.getInfo("employee","personId","personId="+personId);
            String isManager = ""+ DataBaseConnector.getInfo("employee","managerId","personId="+personId);
            if(isManager.equals("null")){
                ManagerWindow managerWindow = new ManagerWindow();
                managerWindow.paint();
                Navigation.setManger("" + DataBaseConnector.getInfo("person", "name", "personId=" + personId));
            }
            else {
                EmployeeWindow employeeWindow = new EmployeeWindow();
                employeeWindow.paint();
                Navigation.setEmplayee("" + DataBaseConnector.getInfo("person", "name", "personId=" + personId));
            }
        }
        else if(!isCustomer){
            Navigation.setUserName(""+ DataBaseConnector.getInfo("person","name","personId="+personId));
        }
        else {}
    }
}


