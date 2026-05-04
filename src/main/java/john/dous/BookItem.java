package john.dous;

// John Dous - Klass för böcker som ärver från MediaItem.
public class BookItem extends MediaItem {
    private String author;
    private String genre;
    private int pages;

    public BookItem(String id, String title, String author, String genre, int pages, boolean isAvailable) {
        super(id, title, isAvailable);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String getInfo() {
        return "Bok - ID: " + getId()
                + ", Titel: " + getTitle()
                + ", Författare: " + author
                + ", Genre: " + genre
                + ", Sidor: " + pages
                + ", Tillgänglig: " + isAvailable();
    }
}