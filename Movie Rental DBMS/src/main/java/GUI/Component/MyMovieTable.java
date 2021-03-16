package GUI.Component;

import DatabaseConnection.DataBaseConnector;
import GUI.SignInWindow;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class MyMovieTable {

    public VBox table(){

        System.out.println(data(SignInWindow.personId));

        TableView<TableObj> table = new TableView<>();


        final Label label = new Label("Your Movies");
        label.setFont(new Font("Arial", 20));

        table.setEditable(false);

        TableColumn firstNameCol = new TableColumn("Movie Title");
        firstNameCol.setMinWidth(200);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<TableObj, String>("title"));

        TableColumn lastNameCol = new TableColumn("Start Date");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<TableObj, String>("startDate"));

        TableColumn emailCol = new TableColumn("Return Date");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<TableObj, String>("returnDate"));

        table.setItems(FXCollections.observableArrayList(data(SignInWindow.personId)));
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(label, table);

        return vbox;
    }

    public ArrayList<TableObj> data(int personId){
        ArrayList<TableObj> arrayList = new ArrayList<>();
        ArrayList hireIds = DataBaseConnector.getInfoList("hireInfo","hireId","customerId="+personId);
        for (int i=0; i<hireIds.size(); i++){
            int movieId = (Integer) DataBaseConnector.getInfo("hireInfo","movieId","hireId="+hireIds.get(i));
            String title = DataBaseConnector.getInfo("movie","title","movieId="+movieId)+"";
            String startDate = DataBaseConnector.getInfo("hireInfo","startDate","hireId="+hireIds.get(i))+"";
            String returnDate = DataBaseConnector.getInfo("hireInfo","returnDate","hireId="+hireIds.get(i))+"";
            TableObj tableObj = new TableObj(title,startDate,returnDate);
            arrayList.add(tableObj);
        }
        return arrayList;
    }

    public class TableObj{

        String title,startDate,returnDate;

        public TableObj(String title, String startDate, String returnDate) {
            this.title = title;
            this.startDate = startDate;
            this.returnDate = returnDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(String returnDate) {
            this.returnDate = returnDate;
        }
    }
}
