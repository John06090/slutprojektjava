package john.dous;

// John Dous - Klass för tidningar som ärver från MediaItem.
public class MagazineItem extends MediaItem {
    private int issueNumber;
    private String category;
    private int publishedYear;

    public MagazineItem(String id, String title, int issueNumber, String category, int publishedYear, boolean isAvailable) {
        super(id, title, isAvailable);
        this.issueNumber = issueNumber;
        this.category = category;
        this.publishedYear = publishedYear;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getCategory() {
        return category;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    @Override
    public String getInfo() {
        return "Tidning - ID: " + getId()
                + ", Titel: " + getTitle()
                + ", Nummer: " + issueNumber
                + ", Kategori: " + category
                + ", År: " + publishedYear
                + ", Tillgänglig: " + isAvailable();
    }
}