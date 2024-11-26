package application.UI;

import java.io.IOException;

import javafx.scene.layout.AnchorPane;

public interface DynamicPaneLoader {
	void loadPane(String fxmlFile, AnchorPane dynamicPane) throws IOException;
}
