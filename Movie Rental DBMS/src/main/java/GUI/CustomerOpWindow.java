package GUI;

import DatabaseConnection.DataBaseConnector;
import GUI.Component.Navigation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.ArrayList;

public class CustomerOpWindow {

    public ArrayList customers = DataBaseConnector.getInfoList("customer","personId","personId>0");

    public void paint(){

        BorderPane bpane = new BorderPane();
        VBox vBox = new VBox(5);

        for(int i=0; i<customers.size(); i++){
            Button delete = new Button("Delete");
            Button showHires = new Button("Show Hires");

            String name = DataBaseConnector.getInfo("person","name","personId="+customers.get(i))+"";
            String email = DataBaseConnector.getInfo("customer","email","personId="+customers.get(i))+"";

            Label label = new Label(name+"  --  "+email+"  --  ");
            Label label1 = new Label(customers.get(i)+"");
            label1.setVisible(false);
            HBox hBox = new HBox(10);

            delete.setOnAction(e->{
                int id = Integer.valueOf(((Label)hBox.getChildren().get(2)).getText());
                boolean on = DataBaseConnector.delete("person","personId="+id);
                if(on){
                    JOptionPane.showMessageDialog(null,"Customer is deleted.");
                    CustomerOpWindow win = new CustomerOpWindow();
                    win.paint();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Customer is not deleted.");
                }
            });

            showHires.setOnAction(e->{
                VBox vBox1 = new VBox(15);
                vBox1.setPadding(new Insets(20,20,20,20));
                int id = Integer.valueOf(((Label)hBox.getChildren().get(2)).getText());
                ArrayList list = DataBaseConnector.getInfoList("hireInfo","hireId","customerId="+id);
                for(int j=0; j<list.size(); j++){
                    String startDate = DataBaseConnector.getInfo("hireInfo","startDate","hireId="+list.get(j))+"";
                    String returnDate = DataBaseConnector.getInfo("hireInfo","returnDate","hireId="+list.get(j))+"";
                    int movieId = (Integer)DataBaseConnector.getInfo("hireInfo","movieId","hireId="+list.get(j));
                    String movieName = DataBaseConnector.getInfo("movie","title","movieId="+movieId)+"";
                    HBox hBox1 = new HBox(30);
                    Label l1 = new Label(movieName);
                    l1.setMinSize(100,10);
                    hBox1.getChildren().addAll(l1,new Label(startDate),new Label(returnDate));
                    hBox1.setAlignment(Pos.CENTER);
                    vBox1.getChildren().addAll(hBox1);
                    vBox1.setPrefSize(770,700);
                    vBox1.setAlignment(Pos.TOP_CENTER);
                }
                bpane.setCenter(new ScrollPane(vBox1));
            });

            HBox hBox1 = new HBox(10);
            hBox1.getChildren().addAll(delete,showHires);
            hBox.getChildren().addAll(hBox1,label,label1);
            HBox.setMargin(label,new Insets(2,2,2,25));
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.setStyle("-fx-border-color: #3c3e40");
            vBox.getChildren().addAll(hBox);
            vBox.setPadding(new Insets(20,20,20,20));
        }

        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(800,680);
        bpane.setCenter(new ScrollPane(vBox));
        bpane.setTop(Navigation.navigation());

        Scene scene = new Scene(bpane,800,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }

}
