package GUI;

import GUI.Component.Navigation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EmployeeWindow {

    private Button customerOp = new Button("Customer Operation");
    private Button movieOp = new Button("Movie Operation");
    GridPane gridPane = new GridPane();

    public void paint(){

        BorderPane bpane = new BorderPane();
        bpane.setTop(Navigation.navigation());
        bpane.setCenter(section());
        Scene scene = new Scene(bpane,810,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }

    public GridPane section(){
        customerOp.setPrefSize(150,150);
        movieOp.setPrefSize(150,150);
        gridPane.add(customerOp,0,0);
        gridPane.add(movieOp,1,0);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);

        customerOp.setOnAction(e->{
            CustomerOpWindow customerOpWindow = new CustomerOpWindow();
            customerOpWindow.paint();
        });

        movieOp.setOnAction(e->{
            MovieOpWindow movieOpWindow = new MovieOpWindow();
            movieOpWindow.paint();
        });

        return gridPane;
    }


}
