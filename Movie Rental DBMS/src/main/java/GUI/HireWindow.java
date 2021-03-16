package GUI;

import DatabaseConnection.DataBaseConnector;
import GUI.Component.Navigation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.time.LocalDate;

public class HireWindow {

    GridPane gridPane = new GridPane();
    TextField day = new TextField();
    TextField dvdNum = new TextField();
    TextArea adressArea = new TextArea();
    Label totalPriceLabel = new Label();
    int movieId;

    public HireWindow(int movieId) {
        this.movieId = movieId;
    }

    public void paint(){

        gridPane.add(new Label("Movie Name : "),0,0);
        gridPane.add(new Label(""+ DataBaseConnector.getInfo("movie","title","movieId="+movieId)),1,0);

        int perdayCost = (Integer) DataBaseConnector.getInfo("movie","perdayCost","movieId="+movieId);
        gridPane.add(new Label("Perday Cost : "),0,1);
        gridPane.add(new Label(""+perdayCost),1,1);

        String adress = DataBaseConnector.getInfo("customer","adress","personId="+SignInWindow.personId)+"";
        if(adress==null){
            adressArea.setPromptText("You must fill this area.");
        }
        else {
            adressArea.setText(adress);
        }

        adressArea.setPrefSize(200,100);
        gridPane.add(new Label("Adress : "),0,2);
        gridPane.add(adressArea,1,2);

        gridPane.add(new Label("Hire Day : "),0,3);
        gridPane.add(day,1,3);

        gridPane.add(new Label("DVD Number : "),0,4);
        gridPane.add(dvdNum,1,4);

        try {
            dvdNum.textProperty().addListener(e->{
                dayAction(perdayCost,Integer.valueOf(day.getText()),Integer.valueOf(dvdNum.getText()));
            });
            day.textProperty().addListener(e->{
                dayAction(perdayCost,Integer.valueOf(day.getText()),Integer.valueOf(dvdNum.getText()));
            });
        }catch (Exception e){}

        Button hireButton = new Button("Hire");
        gridPane.add(totalPriceLabel,0,5);
        gridPane.add(hireButton,1,5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        hireButton.setOnAction(e->{
            try {
                hireAction(Integer.valueOf(day.getText()),perdayCost,Integer.valueOf(dvdNum.getText()));
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"The operation is failed. Please check your entries.");
            }
        });

        BorderPane bpane = new BorderPane();
        bpane.setTop(Navigation.navigation());
        bpane.setCenter(gridPane);
        Scene scene = new Scene(bpane,810,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }

    public void dayAction(int movieCost,int day,int dvd){
        int toatlPrice = movieCost*day*dvd;
        totalPriceLabel.setText("Total Price: "+toatlPrice+"");
    }


    public void hireAction(int day,int movieCost,int dvd){
        //System.out.println(LocalDate.now().plusDays(5));
        if (adressArea.getText().isEmpty() || adressArea.getText().equalsIgnoreCase("null")){
            JOptionPane.showMessageDialog(null,"You Have to fill adress");
        }
        else {
            DataBaseConnector.insertInfo("hireInfo","customerId,startdate,returndate,dvdNum,totalPrice,movieId",
                    SignInWindow.personId+",'"+LocalDate.now()+"',"+"'"+LocalDate.now().plusDays(day)+"',"+dvd+","+(day*dvd*movieCost)+","+movieId);
            System.out.println("burası dogru");
            JOptionPane.showMessageDialog(null,"Hire is successful.");
            DataBaseConnector.updateInfo("customer","adress = "+"'"+adressArea.getText()+"'","personId="+SignInWindow.personId);
        }
    }
}
