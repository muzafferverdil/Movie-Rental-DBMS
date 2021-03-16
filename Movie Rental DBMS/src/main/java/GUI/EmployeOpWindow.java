package GUI;

import DatabaseConnection.DataBaseConnector;
import GUI.Component.Navigation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.ArrayList;

public class EmployeOpWindow {

    private ArrayList workers = DataBaseConnector.getInfoList("employee","personId","personId>0");
    private TextField name = new TextField();
    private RadioButton r1 = new RadioButton("Male");
    private RadioButton r2 = new RadioButton("Female");
    private TextField birthDate = new TextField();
    private TextArea adress = new TextArea();
    private TextField phone = new TextField();
    private TextField salary = new TextField();
    private TextField email2 = new TextField();
    private TextField password = new TextField();
    private Button submit = new Button("Update");
    private Button add = new Button("Add Employee");

    public void paint(){

        BorderPane bpane = new BorderPane();
        VBox vBox = new VBox(5);

        Button addEmployee = new Button("Add Employee");
        vBox.getChildren().add(addEmployee);
        vBox.setAlignment(Pos.CENTER);

        addEmployee.setOnAction(e->{
            GridPane gpane = pane(0);
            gpane.getChildren().remove(submit);
            gpane.add(add,0,8);
            bpane.setCenter(gpane);
        });

        add.setOnAction(e->{
            String gender = r1.isSelected()?"male":"female";
            DataBaseConnector.insertInfo("person","name,gender,birthDate","'"+name.getText()+"','"+gender+
                    "','"+birthDate.getText()+"'");
            int personId = DataBaseConnector.max("personId","person");
            DataBaseConnector.insertInfo("employee","personId,salary,adress,managerId,storeId,email,phone,password",
                    personId+","+salary.getText()+",'"+adress.getText()+"',"+3+","+2+","+"'"+email2.getText()+"','"+phone.getText()+"','"+password.getText()+"'");
            JOptionPane.showMessageDialog(null,"Employee is added.");
            EmployeOpWindow employeOpWindow = new EmployeOpWindow();
            employeOpWindow.paint();
        });

        for(int i=0; i<workers.size(); i++){

            Button delete = new Button("Delete");
            Button update = new Button("Update");
            Button showInfo = new Button("Show Detailed");

            HBox hBox = new HBox(10);
            String name2 = DataBaseConnector.getInfo("person","name","personId="+workers.get(i))+"";
            String email = DataBaseConnector.getInfo("employee","email","personId="+workers.get(i))+"";

            Label label = new Label(name2+"  --  "+email+"  --  ");
            Label label1 = new Label(workers.get(i)+"");
            label1.setVisible(false);

            delete.setOnAction(e->{
                int id = Integer.valueOf(((Label)hBox.getChildren().get(2)).getText());
                String s = DataBaseConnector.getInfo("employee","managerId","personId="+id)+"";
                if(s.equals("null")){
                    JOptionPane.showMessageDialog(null,"You cannot delete manager.");
                }
                else {
                    boolean on = DataBaseConnector.delete("person","personId="+id);
                    System.out.println("silindi");
                    if(on){
                        JOptionPane.showMessageDialog(null,"Employee is deleted.");
                        EmployeOpWindow employeOpWindow = new EmployeOpWindow();
                        employeOpWindow.paint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Employee is not deleted.");
                    }
                }
            });

            showInfo.setOnAction(e->{
                name.setEditable(false);
                phone.setEditable(false);
                birthDate.setEditable(false);
                email2.setEditable(false);
                salary.setEditable(false);
                adress.setEditable(false);
                password.setEditable(false);
                int id = Integer.valueOf(((Label)hBox.getChildren().get(2)).getText());
                GridPane gpane = pane(id);
                gpane.getChildren().removeAll(submit);
                bpane.setCenter(gpane);
            });

            update.setOnAction(e->{
                name.setEditable(true);
                phone.setEditable(true);
                birthDate.setEditable(true);
                email2.setEditable(true);
                salary.setEditable(true);
                adress.setEditable(true);
                password.setEditable(true);
                int id = Integer.valueOf(((Label)hBox.getChildren().get(2)).getText());
                bpane.setCenter(pane(id));
            });

            HBox hBox1 = new HBox(10);
            hBox1.getChildren().addAll(delete,showInfo,update);
            hBox.getChildren().addAll(hBox1,label,label1);
            HBox.setMargin(label,new Insets(2,2,2,25));
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.setStyle("-fx-border-color: #3c3e40");
            vBox.getChildren().addAll(hBox);
        }
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(800,680);
        bpane.setCenter(new ScrollPane(vBox));
        bpane.setTop(Navigation.navigation());

        Scene scene = new Scene(bpane,800,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }

    public GridPane pane(int id){

        System.out.println(id);
        if(id!=0) {
            name.setText(DataBaseConnector.getInfo("person", "name", "personId=" + id) + "");
            phone.setText(DataBaseConnector.getInfo("employee", "phone", "personId=" + id) + "");
            birthDate.setText(DataBaseConnector.getInfo("person", "birthDate", "personId=" + id) + "");
            email2.setText(DataBaseConnector.getInfo("employee", "email", "personId=" + id) + "");
            salary.setText(DataBaseConnector.getInfo("employee", "salary", "personId=" + id) + "");
            adress.setText(DataBaseConnector.getInfo("employee", "adress", "personId=" + id) + "");
            password.setText(DataBaseConnector.getInfo("employee", "password", "personId=" + id) + "");
        }
        adress.setPrefSize(200,100);

        String gender = DataBaseConnector.getInfo("person","gender","personId="+id)+"";
        if(gender.equals("male")){
            r1.setSelected(true);
        }
        else {
            r2.setSelected(true);
        }

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Full Name : "),0,0);
        gridPane.add(name,1,0);
        gridPane.add(new Label("Phone : "),0,1);
        gridPane.add(phone,1,1);
        gridPane.add(new Label("Gender : "),0,2);
        HBox hBox2 = new HBox(3);
        ToggleGroup toggleGroup = new ToggleGroup();
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
        hBox2.getChildren().addAll(r1,r2);
        gridPane.add(hBox2,1,2);
        gridPane.add(new Label("Birthday : "),0,3);
        gridPane.add(birthDate,1,3);
        gridPane.add(new Label("Email :"),0,4);
        gridPane.add(email2,1,4);
        gridPane.add(new Label("Salary :"),0,5);
        gridPane.add(salary,1,5);
        gridPane.add(new Label("Adress :"),0,6);
        gridPane.add(adress,1,6);
        gridPane.add(new Label("Password :"),0,7);
        gridPane.add(password,1,7);
        gridPane.add(submit,0,8);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(10);

        submit.setOnAction(e->{
            DataBaseConnector.updateInfo("person","name= '"+name.getText()+"'","personId="+id);
            DataBaseConnector.updateInfo("employee","phone= '"+phone.getText()+"'","personId="+id);
            DataBaseConnector.updateInfo("person","gender= "+(r1.isSelected()?"'male'":"'female'"),"personId="+id);
            DataBaseConnector.updateInfo("person","birthDate= '"+birthDate.getText()+"'","personId="+id);
            DataBaseConnector.updateInfo("employee","email= '"+email2.getText()+"'","personId="+id);
            DataBaseConnector.updateInfo("employee","adress= '"+adress.getText()+"'","personId="+id);
            DataBaseConnector.updateInfo("employee","salary= '"+salary.getText()+"'","personId="+id);
            DataBaseConnector.updateInfo("employee","password= '"+password.getText()+"'","personId="+id);
            JOptionPane.showMessageDialog(null,"Update Operation is successful.");
            EmployeOpWindow employeOpWindow = new EmployeOpWindow();
            employeOpWindow.paint();
        });

        return gridPane;
    }
}
