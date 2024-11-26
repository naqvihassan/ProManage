package application.UI;

public interface MeetingsLoader {
	  public void loadMeetings() throws Exception;
	  public void loadMissingMeetings() throws Exception;
	  public void loadPendingMeetings() throws Exception;
	  public void loadCompletedMeetings() throws Exception;
	  public void loadScheduledMeetings() throws Exception;
	  public void loadCancelledMeetings() throws Exception;

}
