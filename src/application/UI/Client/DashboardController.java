package application.UI.Client;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
public class DashboardController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;
    
    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private AnchorPane dynamicPane;
    
    public void initialize() throws IOException {
        toggleActive(button1, button2, button3, button4, button5);
        loadPane1();
        }

    public void loadPane1() throws IOException {
        loadDynamicPane("/application/UI/Client/ClientDashboard.fxml");
    }

    // Method to load pane2 into the dynamic pane
    public void loadPane2() throws IOException {
        loadDynamicPane("/application/UI/Client/Notifications.fxml");
    }

    // Method to load pane3 into the dynamic pane
    public void loadPane3() throws IOException {
        loadDynamicPane("/application/UI/Client/Tasks.fxml");
    }
    
    public void loadPane4() throws IOException {
        loadDynamicPane("/application/UI/Client/Meetings.fxml");
    }

    // Method to load pane3 into the dynamic pane
    public void loadPane5() throws IOException {
        loadDynamicPane("/application/UI/Client/ManageAccount.fxml");
    }
    
    private void loadDynamicPane(String fxmlFile) throws IOException {
        try {
            // Load the FXML and set it in the dynamicPane
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile));
            dynamicPane.getChildren().clear();
            dynamicPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toggleActive(Button activeButton, Button... otherButtons) {
        // Set active button style
        activeButton.getStyleClass().add("active");
        // Remove active style from other buttons
        for (Button button : otherButtons) {
            button.getStyleClass().remove("active");
        }
    }

    // Event handler for Button 1
    @FXML
    private void handleButton1Click(ActionEvent event) throws IOException {
        // Clear previous content
        toggleActive(button1, button2, button3, button4, button5);
        // Add new content (for Button 1)
        loadPane1();
    }

    // Event handler for Button 2
    @FXML
    private void handleButton2Click(ActionEvent event) throws IOException {
        // Clear previous content
        dynamicPane.getChildren().clear();
        // Add new content (for Button 2)
        toggleActive(button2, button5, button1, button3, button4);
        // Add new content (for Button 1)
        loadPane2();
    }

    // Event handler for Button 3
    @FXML
    private void handleButton3Click(ActionEvent event) throws IOException {
        // Clear previous content
        dynamicPane.getChildren().clear();
        // Add new content (for Button 3)
        toggleActive(button3, button2, button1, button4, button5);
        // Add new content (for Button 1)
        loadPane3();
    }

    // Event handler for Button 4 (Add additional logic as needed)
    @FXML
    private void handleButton4Click(ActionEvent event) throws IOException {
        // Clear previous content
        dynamicPane.getChildren().clear();
        // Add new content (for Button 4)
        toggleActive(button4, button2, button1, button3, button5);
        // Add new content (for Button 1)
        loadPane4();
    }

    // Event handler for Button 5 (Add additional logic as needed)
    @FXML
    private void handleButton5Click(ActionEvent event) throws IOException {
        // Clear previous content
        dynamicPane.getChildren().clear();
        // Add new content (for Button 5)
        toggleActive(button5, button2, button1, button3, button4);
        // Add new content (for Button 1)
        loadPane5();
      
    }
}
