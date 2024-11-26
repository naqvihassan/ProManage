package application.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import application.BL.Model.Meeting;
import javafx.scene.control.Label;

public class BaseMeetingDetailController {
		
		protected void ShowMeetingDetail(Label title, Label admin, Label deadline, Label description, Meeting m) {
		title.setPrefWidth(1000);
	    title.setText(m.getPurpose());
	    admin.setPrefWidth(400);
	    String cl = m.getCreatedAt();
	    System.out.println(cl);
	    DateTimeFormatter createdAtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	    LocalDateTime ct = LocalDateTime.parse(cl, createdAtFormatter);
	    DateTimeFormatter createdAtOutput = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
	    String formattedCreatedAt = ct.format(createdAtOutput);
	    admin.setText(m.getAdminName()+ " • " + formattedCreatedAt);
	    String dl = m.getScheduledAt().toString();
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	    LocalDateTime ddl = LocalDateTime.parse(dl, inputFormatter);
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy , hh':'mm a");
	    String formattedDeadline = ddl.format(outputFormatter);
	    deadline.setPrefWidth(300);
	    deadline.setText("Scheduled at: " + formattedDeadline);
	    description.setPrefWidth(1000);
	    description.setText(m.getDescription());
		}
	}
