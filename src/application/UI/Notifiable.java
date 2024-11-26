package application.UI;

public interface Notifiable {
    public void loadAll() throws Exception;
    public void loadRead() throws Exception;
    public void loadUnread() throws Exception;
}
