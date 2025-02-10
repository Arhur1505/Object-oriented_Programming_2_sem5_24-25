final class Book extends LibraryItem {
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private int pagesNr = 100;

    public Book(int id, String title, String author, String genre, String publisher) {
        super(id);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public Book() {
        super(0);
        this.title = "";
        this.author = "";
        this.genre = "";
        this.publisher = "";
    }

    private Book(int id, String author) {
        super(id);
        this.title = "Default Title";
        this.author = author;
    }

    public Book(String title) {
        super(0);
        this.title = title;
    }

    @Override
    public int getLoanPeriod(boolean isStudent) {
        return 14;
    }

    @Override
    public double getOverdueFineRate() {
        return 0.5;
    }
}