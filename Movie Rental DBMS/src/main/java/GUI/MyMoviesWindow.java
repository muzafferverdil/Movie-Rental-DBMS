package GUI;

import GUI.Component.MyMovieTable;
import GUI.Component.Navigation;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MyMoviesWindow {

    public void paint(){

        MyMovieTable myMovieTable = new MyMovieTable();

        BorderPane bpane = new BorderPane();

        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().addAll(myMovieTable.table());

        bpane.setCenter(stackPane);
        bpane.setTop(Navigation.navigation());

        Scene scene = new Scene(bpane,810,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }
}
