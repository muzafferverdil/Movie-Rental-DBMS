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

import java.util.ArrayList;

public class MovieOpWindow {

    public ArrayList movies = DataBaseConnector.getInfoList("movie","movieId","movieId>0");
    public Button add = new Button("Add Movie");

    public void paint(){
        BorderPane bpane = new BorderPane();
        VBox vBox = new VBox(5);

        for(int i=0; i<movies.size(); i++){
            String title = DataBaseConnector.getInfo("movie","title","movieId="+movies.get(i))+"";

            Label label = new Label(title+"  --  ");
            label.setMinWidth(100);
            Label label1 = new Label(movies.get(i)+"");
            HBox hBox = new HBox(10);
            hBox.setMinWidth(250);
            hBox.getChildren().addAll(label,label1);

            HBox hBox1 = new HBox(20);
            hBox1.setAlignment(Pos.TOP_CENTER);
            hBox1.getChildren().addAll(hBox);
            HBox.setMargin(label,new Insets(2,2,2,25));
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.setStyle("-fx-border-color: #3c3e40");
            vBox.getChildren().addAll(hBox1);
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.setPadding(new Insets(18,18,18,18));
        }

        add.setOnAction(e->{
            MovieAddWindow movieAddWindow = new MovieAddWindow();
            movieAddWindow.paint();
        });

        vBox.getChildren().add(0,add);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinSize(790,680);
        bpane.setCenter(new ScrollPane(vBox));
        bpane.setTop(Navigation.navigation());

        Scene scene = new Scene(bpane,800,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }
}
