package GUI;

import DatabaseConnection.DataBaseConnector;
import GUI.Component.Navigation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.time.LocalDate;

public class MovieAddWindow {

    public void paint(){

        GridPane gpane = new GridPane();

        TextField title = new TextField();
        TextField cost = new TextField();
        TextField length = new TextField();
        TextField country = new TextField();
        TextField releasesDate = new TextField();
        releasesDate.setPromptText("YYYY-MM-DD");
        TextField descr = new TextField();
        TextField imdb = new TextField();
        Button add = new Button("Add Movie");

        gpane.add(new Label("Movie Title"),0,0);
        gpane.add(title,1,0);

        gpane.add(new Label("Perday Cost"),0,1);
        gpane.add(cost,1,1);

        gpane.add(new Label("Length"),0,2);
        gpane.add(length,1,2);

        gpane.add(new Label("Country"),0,3);
        gpane.add(country,1,3);

        gpane.add(new Label("Releases Date"),0,4);
        gpane.add(releasesDate,1,4);

        gpane.add(new Label("Description"),0,5);
        gpane.add(descr,1,5);

        gpane.add(new Label("IMDB"),0,6);
        gpane.add(imdb,1,6);

        gpane.add(add,0,7);

        add.setOnAction(e->{
           boolean on = DataBaseConnector.insertInfo("movie","title,perdayCost,length,country,releaseDate,description,imdb,uploadTime,picture,directorId",
                    "'"+ title.getText()+"',"+cost.getText()+","+length.getText()+
                    ",'"+country.getText()+"','"+releasesDate.getText()+"','"+descr.getText()+"',"+imdb.getText()+",'"+ LocalDate.now()+"','"+"default.jpg"+"',"+2);
           if(on){
               JOptionPane.showMessageDialog(null,"Adding is succesfully.");
               title.clear();
               cost.clear();
               length.clear();
               releasesDate.clear();
               descr.clear();
               imdb.clear();
               country.clear();
               MovieOpWindow movieOpWindow = new MovieOpWindow();
               movieOpWindow.paint();
           }
           else {
               JOptionPane.showMessageDialog(null,"Adding is failed.");
           }
        });

        gpane.setAlignment(Pos.CENTER);
        gpane.setHgap(10);
        gpane.setVgap(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gpane);
        borderPane.setTop(Navigation.navigation());
        Scene scene = new Scene(borderPane,800,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }
}
