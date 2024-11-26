package application.UI;

public interface TasksLoader {
	  public void loadTasks() throws Exception;
	  public void loadMissingTasks() throws Exception;
	  public void loadPendingTasks() throws Exception;
	  public void loadCompletedTasks() throws Exception;
}
