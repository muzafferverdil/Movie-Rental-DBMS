package GUI;

import DatabaseConnection.DataBaseConnector;
import GUI.Component.Navigation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.ArrayList;

public class MovieWindow {

    private int id;

    public MovieWindow(int id){

        this.id = id;
    }

    public void paint(){
        BorderPane bpane = new BorderPane();
        bpane.setTop(Navigation.navigation());
        bpane.setCenter(new ScrollPane(section()));
        ScrollPane scrollPane = new ScrollPane(bottom());
        scrollPane.setPrefViewportHeight(80);
        bpane.setBottom(scrollPane);
        Scene scene = new Scene(bpane,810,700);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("m.css").toExternalForm());
        Launcher.changeScene(scene);
    }

    public HBox section(){

        String condition = "movieId"+"="+id;

        HBox hBox = new HBox(40);
        VBox vBox1 = new VBox(20);
        vBox1.setId("vb2");
        vBox1.setAlignment(Pos.TOP_CENTER);
        VBox vBox2 = new VBox(10);
        vBox2.setId("vb3");
        Image image = new Image(ClassLoader.getSystemClassLoader().getResource(String.valueOf(DataBaseConnector.getInfo("movie","picture",condition))).toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        Button button = new Button("Hire Movie");
        vBox1.getChildren().addAll(imageView,button);
        Label l1 = new Label("Movie Name : "+ DataBaseConnector.getInfo("movie","title",condition));
        Label l2 = new Label("Movie Length : "+ DataBaseConnector.getInfo("movie","length",condition));
        Label l3 = new Label("Movie Country : "+ DataBaseConnector.getInfo("movie","country",condition));
        Label l4 = new Label("Imdb Score : "+ DataBaseConnector.getInfo("movie","imdb",condition));
        Label l5 = new Label("Movie Release Date: "+ DataBaseConnector.getInfo("movie","releaseDate",condition));
        Label l6 = new Label("Movie Description : \n"+ DataBaseConnector.getInfo("movie","description",condition));
        ArrayList catgList = DataBaseConnector.getInfoList("movieCategory","catgName","movieId = "+id);
        String categries = "";
        for(int i=0; i<catgList.size(); i++){
            categries = catgList.get(i)+" , "+categries;
        }
        Label l6_5 = new Label("Movie Type : "+categries);
        vBox2.getChildren().addAll(l1,l2,l3,l4,l5,l6,l6_5);

        Label l7 = new Label("Movie Language : ");
        vBox2.getChildren().addAll(l7);
        ArrayList langList = DataBaseConnector.getInfoList("movielang","langName","movieId="+id);
        System.out.println("Size: "+langList.size());
        for(int i=0; i<langList.size(); i++){
            String condition2 = "("+"langName"+","+"movieId"+")"+"="+"("+"\""+langList.get(i)+"\""+","+id+")";
            Label l8 = new Label("Language : "+langList.get(i)+" , "+
                    "Mother Tong: "+ DataBaseConnector.getInfo("movielang","motherTong",condition2)+" , "+
                    " Subtitles : "+ DataBaseConnector.getInfo("movielang","subtitles",condition2)+" , "+
                    " Dubbing : "+ DataBaseConnector.getInfo("movielang","dubbing",condition2));
            vBox2.getChildren().addAll(l8);
        }
        int directorId = (Integer) DataBaseConnector.getInfo("movie","directorId",condition);
        Label l9 = new Label("Movie Director : "+ DataBaseConnector.getInfo("person","name","personId = "+directorId)+" - "+ DataBaseConnector.getInfo("director","company","personId = "+directorId));
        vBox2.getChildren().addAll(l9);

        ArrayList<Object> producers = DataBaseConnector.getInfoList("produced","producerId","movieId="+id);
        Label l10 = new Label("Movie Producer : ");
        vBox2.getChildren().addAll(l10);
        for(int i=0; i<producers.size(); i++){
            Label l11 = new Label(String.valueOf(DataBaseConnector.getInfo("person","name","personId ="+producers.get(i))));
            vBox2.getChildren().addAll(l11);
        }

        Label l12 = new Label("Movie Actors : ");
        vBox2.getChildren().addAll(l12);
        ArrayList<Object> list = DataBaseConnector.getInfoList("acted","actorId","movieId="+id);
        for(int i=0; i<list.size(); i++){
            Label l13 = new Label((DataBaseConnector.getInfo("person","name","personId = "+list.get(i)))+" - "+
                    DataBaseConnector.getInfo("acted","roles","actorId = "+list.get(i)));
            vBox2.getChildren().addAll(l13);
        }

        button.setOnAction(e->{
            if(SignInWindow.on){
                HireWindow hireWindow = new HireWindow(id);
                hireWindow.paint();
            }
            else {
                JOptionPane.showMessageDialog(null,"You should sign in.");
            }
        });

        hBox.getChildren().addAll(vBox1,vBox2);
        return hBox;
    }

    public VBox bottom(){
        ArrayList<Object> list = DataBaseConnector.getInfoList("comment","personId","movieId="+id);
        VBox vBox = new VBox(5);
        vBox.setId("vb4");

        for (int i=0; i<list.size(); i++){
            Label label = new Label(DataBaseConnector.getInfo("person","name","personId="+list.get(i))+" --> "+
                    DataBaseConnector.getInfo("comment","comment","(movieId,personId)=("+id+","+list.get(i)+")"));
            vBox.getChildren().addAll(label);
        }
        return  vBox;
    }

}
