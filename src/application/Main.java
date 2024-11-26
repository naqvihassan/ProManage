package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML with an absolute path
            Parent root = FXMLLoader.load(getClass().getResource("/application/UI/Login.fxml"));

            // Load CSS with an absolute path
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

            // Set up the primary stage
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}