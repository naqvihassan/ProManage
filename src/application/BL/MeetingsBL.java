package application.BL;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import application.BL.Model.Meeting;
import application.DB.MeetingsDB;
import application.DB.TasksDB;

public class MeetingsBL {
	public static List<Meeting> getAllMeetings(int id) throws Exception {
        return MeetingsDB.fetchAllMeetings(id);
    }
    
    public static List<Meeting> getMissingMeetings(int id) throws Exception {
        return MeetingsDB.fetchMissingMeetings(id);
    }
    
    public static List<Meeting> getPendingMeetings(int id) throws Exception {
        return MeetingsDB.fetchPendingMeetings(id);
    }
    
    public static List<Meeting> getCompletedMeetings(int id) throws Exception {
        return MeetingsDB.fetchCompletedMeetings(id);
    }
    
    public static List<Meeting> getScheduledMeetings(int id) throws Exception {
        return MeetingsDB.fetchScheduledMeetings(id);
    }
    
    public static List<Meeting> getCancelledMeetings(int id) throws Exception {
        return MeetingsDB.fetchCancelledMeetings(id);
    }
    
    public static Meeting getMeetingbyMeetingid(int id) throws SQLException {
    	return MeetingsDB.getMeetingbyMeetingId(id);
    }
    public static boolean updateMeetingStatus(String s, int id) throws Exception {
    	return MeetingsDB.updateMeetingStatus(s, id);
    }
    public static boolean updateMeetingRequest(String s, int id) throws Exception {
    	return MeetingsDB.updateMeetingRequest(s, id);
    }
    
    public static boolean scheduleMeeting(int admin_id, int client_id, String meetingTitle, String meetingDescription,Timestamp scheduledAt, int duration) {
    	try {
            // Call DB Layer to clear file details for the task
            return MeetingsDB.scheduleMeeting(admin_id, client_id,meetingTitle,meetingDescription,scheduledAt, duration);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static int getMeetingsCreatedCountByDays(int n, int id) {
   	 try {
            return MeetingsDB.getMeetingsCreatedLastNDays(n,id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
   }
   
   public static int getMeetingsCompletedCountByDays(int n, int id) {
  	 try {
           return MeetingsDB.getMeetingsCreatedLastNDaysWithStatus(n,id);
       } catch (Exception e) {
           e.printStackTrace();
           return 0;
       }
  }
   public static void updateMeetingStatusesForDeadlines(int id) {
       try {
           // Call the updateMeetingStatusesForDeadlines method from the database (DB) layer
           boolean success = MeetingsDB.updateMeetingStatusesForDeadlines(id);
           
           // Optionally handle the success or failure based on the returned value
           if (success) {
               System.out.println("Meeting statuses updated successfully.");
           } else {
               System.out.println("No meeting statuses were updated.");
           }
       } catch (Exception e) {
           // Handle any exceptions that occur during the operation
           e.printStackTrace();
       }
   }
}
