package application.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.BL.Model.Task;
import javafx.scene.control.Label;

public class BaseTaskDetailController {
	
	protected void ShowTaskDetail(Label title, Label admin, Label deadline, Label description, Task t) {
	title.setPrefWidth(1000);
    title.setText(t.getTitle());
    admin.setPrefWidth(400);
    String cl = t.getCreatedAt();
    System.out.println(cl);
    DateTimeFormatter createdAtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    LocalDateTime ct = LocalDateTime.parse(cl, createdAtFormatter);
    DateTimeFormatter createdAtOutput = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
    String formattedCreatedAt = ct.format(createdAtOutput);
    admin.setText(t.getSupervisorName() + " • " + formattedCreatedAt);
    String dl = t.getDeadline().toString();
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    LocalDateTime ddl = LocalDateTime.parse(dl, inputFormatter);
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
    String formattedDeadline = ddl.format(outputFormatter);
    deadline.setPrefWidth(300);
    deadline.setText("Due: " + formattedDeadline);
    description.setPrefWidth(1000);
    description.setText(t.getDescription());
	}
}
