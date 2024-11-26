module ProManage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires javafx.graphics;
	requires java.desktop;

    // Allow JavaFX to access the `application.UI` package
    opens application.UI to javafx.fxml;
    
    // Allow JavaFX to access the `application.UI.Client` package
    opens application.UI.Client to javafx.fxml;
    opens application.UI.Admin to javafx.fxml;

    
    // You might still need this for other classes in `application`
    opens application to javafx.graphics, javafx.fxml;
    
    opens application.DB to javafx.fxml;
    opens application.BL to javafx.fxml;

}