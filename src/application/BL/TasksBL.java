package application.BL;

import application.DB.TasksDB;
import java.sql.Timestamp;
import application.BL.Model.Task;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TasksBL {
    public static List<Task> getAllTasks(int id) throws Exception {
        return TasksDB.fetchAllTasks(id);
    }
    
    public static List<Task> getMissingTasks(int id) throws Exception {
        return TasksDB.fetchMissingTasks(id);
    }
    
    public static List<Task> getPendingTasks(int id) throws Exception {
        return TasksDB.fetchPendingTasks(id);
    }
    
    public static List<Task> getCompletedTasks(int id) throws Exception {
        return TasksDB.fetchCompletedTasks(id);
    }
    
    public static List<Task> getAllTasksforAdmin(int id) throws Exception{
    	return TasksDB.fetchAllTasksForAdmin(id);
    }
    
    public static List<Task> getMissingTasksForAdmin(int id) throws Exception {
        return TasksDB.fetchMissingTasksForAdmin(id);
    }
    
    public static List<Task> getPendingTasksForAdmin(int id) throws Exception {
        return TasksDB.fetchPendingTasksForAdmin(id);
    }
    
    public static List<Task> getCompletedTasksForAdmin(int id) throws Exception {
        return TasksDB.fetchCompletedTasksForAdmin(id);
    }
    
    public static Task getTaskbyTaskId(int id) {
    	return TasksDB.getTaskById(id);
    }
    
    public static boolean updateTaskWithFile(int taskId, File file) {
        try (FileInputStream fileData = new FileInputStream(file)) {
            String fileType = getFileExtension(file);
            long fileLength = file.length();
            String fileName = getFileNameWithoutExtension(file);
            // Call DB Layer
            return TasksDB.updateTaskFile(taskId, fileData, fileType, fileLength, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static String getFileNameWithoutExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) { // Ensure there's an extension
            return fileName.substring(0, dotIndex);
        }
        return fileName; // Return as is if no extension found
    }
    
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    
    public static boolean assignTask(int admin_id, int client_id, String taskTitle, String taskDescription,Timestamp dueDateTime) {
    	try {
            // Call DB Layer to clear file details for the task
            return TasksDB.assignTask(admin_id, client_id,taskTitle,taskDescription,dueDateTime);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static boolean unsubmitTask(int taskId) {
        try {
            // Call DB Layer to clear file details for the task
            return TasksDB.clearTaskFileDetails(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static int getTasksCreatedCountByDays(int n, int id) {
    	 try {
             // Call DB Layer to clear file details for the task	
             return TasksDB.getTasksCreatedLastNDays(n,id);
         } catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
    }
    
    public static int getTasksCompletedCountByDays(int n, int id) {
   	 try {
            // Call DB Layer to clear file details for the task	
            return TasksDB.getTasksCreatedLastNDaysWithStatus(n,id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
   }
    
    public static List<Task> getTasksDueToday(int id) {
    	 return TasksDB.getTasksDueToday(id);
    }
    
    public static List<Task> getTasksDueNextWeek(int id) {
   	 return TasksDB.getTasksDueInNext7Days(id);
   }

    public static void updateTaskStatusesForDeadlines(int id) {
        try {
            // Call the updateTaskStatusesForDeadlines method and check the result
            boolean success = TasksDB.updateTaskStatusesForDeadlines(id);
            
            // Optionally handle the success or failure based on the returned value
            if (success) {
                System.out.println("Task statuses updated successfully.");
            } else {
                System.out.println("No task statuses were updated.");
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the operation
            e.printStackTrace();
        }
    }


}
