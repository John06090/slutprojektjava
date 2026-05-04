package john.dous;

// John Dous - Basklass för mediaobjekt i bibliotekssystemet.
public class MediaItem {
    private String id;
    private String title;
    private boolean isAvailable;

    public MediaItem(String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getInfo() {
        return "ID: " + id + ", Titel: " + title + ", Tillgänglig: " + isAvailable;
    }
}