package application.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import application.DB.UserDB;

public abstract class BaseUIController implements DynamicPaneLoader {

    @Override
    public void loadPane(String fxmlFile, AnchorPane dynamicPane) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile));
            dynamicPane.getChildren().clear();
            dynamicPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace(); // Debugging purpose
            throw e; // Propagate exception
        }
    }

    /**
     * Toggles active button styling.
     *
     * @param activeButton the active button
     * @param otherButtons the other buttons to deactivate
     */
    protected void toggleActive(Button activeButton, Button... otherButtons) {
        activeButton.getStyleClass().add("active");
        for (Button button : otherButtons) {
            button.getStyleClass().remove("active");
        }
    }
    
    
    protected void toggleActiveFilter(Button activeButton, Button... otherButtons) {
        // Set active button style
    	activeButton.setStyle("-fx-background-color: #4f46E5; -fx-text-fill: white; -fx-cursor: HAND; -fx-background-radius:8;");
        // Remove active style from other buttons
        for (Button button : otherButtons) {
        	button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-cursor: HAND; -fx-background-radius:8;");       
        	}
    }

    /**
     * Displays an alert message.
     *
     * @param title   the alert title
     * @param message the alert message
     * @param type    the alert type
     */
    protected void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Abstract method to be implemented by child classes for initialization.
     * 
     * @throws Exception initialization error
     */
    public abstract void initialize() throws Exception;
}
