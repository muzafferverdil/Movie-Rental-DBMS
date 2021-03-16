package GUI;

import DatabaseConnection.DataBaseConnector;
import GUI.Component.Navigation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.swing.*;

public class SignUpWindow {

    TextField name;
    TextField phone;
    RadioButton radioButton;
    RadioButton radioButton2;
    TextField birthDate;
    TextField email;
    TextField country;
    PasswordField pswd;

    public void paint(){
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(section());
        borderPane.setTop(Navigation.navigation());
        Scene scene = new Scene(borderPane,800,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }

    public GridPane section(){

        GridPane gridPane = new GridPane();

        name = new TextField();
        phone = new TextField();
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton = new RadioButton();
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setGraphic(new Label("Male"));
        radioButton2 = new RadioButton();
        radioButton2.setToggleGroup(toggleGroup);
        radioButton2.setGraphic(new Label("Female"));
        birthDate = new TextField();
        birthDate.setPromptText("YYYY-MM-DD");
        email = new TextField();
        email.setPromptText("example@gmail.com");
        country = new TextField();
        pswd = new PasswordField();

        Button submit = new Button("Submit");
        submit.setOnAction(e->{
            submitAction();
        });

        gridPane.add(new Label("Full Name : "),0,0);
        gridPane.add(name,1,0);
        gridPane.add(new Label("Phone : "),0,1);
        gridPane.add(phone,1,1);
        gridPane.add(new Label("Gender : "),0,2);
        HBox hBox = new HBox(3);
        hBox.getChildren().addAll(radioButton,radioButton2);
        gridPane.add(hBox,1,2);
        gridPane.add(new Label("Birthday : "),0,3);
        gridPane.add(birthDate,1,3);
        gridPane.add(new Label("Email :"),0,4);
        gridPane.add(email,1,4);
        gridPane.add(new Label("Password :"),0,5);
        gridPane.add(pswd,1,5);
        gridPane.add(submit,0,6);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);

        return gridPane;
    }

    public void submitAction(){

        boolean validate = DataBaseConnector.checkValidation("customer","email","email = "+"'"+email.getText()+"'");

        if(validate){
            System.out.println(validate);
            String gender = radioButton.isSelected()?"male":"female";
            String value =  "'"+name.getText()+"',"+"'"+gender+"',"+"'"+birthDate.getText()+"'";
            DataBaseConnector.insertInfo("person","name, gender, birthDate",value);
            String value2 = DataBaseConnector.max("personId","person")+",'"+email.getText()+"',"+"'"+pswd.getText()+"'";
            DataBaseConnector.insertInfo("customer","personId,email,password",value2);
            JOptionPane.showMessageDialog(null,"Registration is successfull.");
            name.clear();
            birthDate.clear();
            email.clear();
            pswd.clear();
            phone.clear();
        }
        else {
            JOptionPane.showMessageDialog(null,"This email is used.");
        }
    }
}
