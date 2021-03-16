package GUI;

import DatabaseConnection.DataBaseConnector;
import com.ibatis.common.jdbc.ScriptRunner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.Optional;

import static java.nio.file.Paths.*;

public class Launcher extends Application {

    private static Stage stage = new Stage();
    TextField username = new TextField();
    PasswordField password = new PasswordField();

    @Override
    public void start(Stage primaryStage) {



        if(!DataBaseConnector.isthereDB()){
            message();
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
                Connection con = DriverManager.getConnection(mysqlUrl, username.getText(), password.getText());
                System.out.println("Connection established......");
                //Initialize the script runner
                ScriptRunner sr = new ScriptRunner(DataBaseConnector.createConnection(),false,false);
                //Creating a reader object
                System.out.println(getClass().getClassLoader().getResource("MovieRentalSql.sql").toString());

                URI uri = getClass().getClassLoader().getResource("MovieRentalSql.sql").toURI();
                if("jar".equals(uri.getScheme())){
                    for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
                        if (provider.getScheme().equalsIgnoreCase("jar")) {
                            try {
                                provider.getFileSystem(uri);
                            } catch (FileSystemNotFoundException e) {
                                // in this case we need to initialize it first:
                                try {
                                    provider.newFileSystem(uri, Collections.emptyMap());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
                Path path = get(uri);
                //Running the script
                sr.runScript(Files.newBufferedReader(path));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        MainWindow mainWindow = new MainWindow();
        stage.setScene(mainWindow.paint());
        stage.setResizable(false);
        stage.setTitle("Movie Rental");
        stage.show();
    }

    public static void changeScene(Scene scene){
        stage.setScene(scene);
    }

    public void message(){
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setHeaderText("Mysql Login");

// Set the icon (must be included in the project).

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        username.setPromptText("Username");
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }
}


