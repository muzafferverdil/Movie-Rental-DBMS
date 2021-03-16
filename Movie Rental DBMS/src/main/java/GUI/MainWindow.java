package GUI;

import DatabaseConnection.DataBaseConnector;
import DatabaseConnection.Entities.Movie;
import GUI.Component.Navigation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainWindow {

    private ArrayList movies = DataBaseConnector.getMovies();

    public MainWindow(){
    }

    public Scene paint(){
        BorderPane bpane = new BorderPane();
        bpane.setTop(Navigation.navigation());
        bpane.setCenter(section());

        Scene scene = new Scene(bpane,800,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        movies.clear();
        return scene;
    }

    public VBox section(){
        VBox vBox = new VBox(10);

        HBox hBox1 = new HBox();
        hBox1.setId("hbox1");
        Label label = new Label("New Releases Movies");
        hBox1.getChildren().add(label);

        vBox.getChildren().addAll(hBox1,new ScrollPane(moviePane()));
        return vBox;
    }

    public VBox moviePane(){

        HBox hBox = new HBox(30);
        VBox vBox = new VBox(20);
        vBox.setId("vb");

        int size = movies.size();
        System.out.println("Size "+size);

        for(int i=0; i<size; i++){
            System.out.println(((Movie)movies.get(i)).getPicture());
            Image image = new Image(ClassLoader.getSystemClassLoader().getResource(((Movie)movies.get(i)).getPicture()).toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);

            Label label = new Label(((Movie)movies.get(i)).getName()+"\n"+"IMDB Score: "+((Movie)movies.get(i)).getImdb());

            CustomVBox customVBox = new CustomVBox(imageView,label,((Movie)movies.get(i)).getId());
            VBox vBox1 = customVBox.getVBox();
            vBox1.setOnMouseClicked(e -> {
                mouseAction(customVBox.id);
            });


            hBox.getChildren().add(vBox1);
            if(hBox.getChildren().size()==3){
                vBox.getChildren().add(hBox);
                hBox = new HBox(30);
            }
            else if((i+1) == size){
                vBox.getChildren().add(hBox);
            }

        }

        return vBox;
    }

    public void mouseAction(int id){
       MovieWindow movieWindow = new MovieWindow(id);
       movieWindow.paint();
    }

    class CustomVBox{

        VBox vBox = new VBox(5);
        ImageView imw;
        Label lab;
        int id;

        public CustomVBox(ImageView imw, Label lab, int id) {
            this.imw = imw;
            this.lab = lab;
            this.id = id;
        }

        public VBox getVBox(){
            vBox.getChildren().addAll(imw,lab);
            return vBox;
        }
    }

}
