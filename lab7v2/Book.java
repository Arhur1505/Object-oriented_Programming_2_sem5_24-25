public class Book extends LibraryItem{
    private String title;
    private String author;
    private String genre;
    private String publisher;

    public Book(int id, String title, String author, String genre, String publisher){
        super(id);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    @Override
    public int getBorrowingPeriod(boolean isStudent){
        return 14;
    }

    @Override
    public double getFinePerDay(){
        return 0.5;
    }
}