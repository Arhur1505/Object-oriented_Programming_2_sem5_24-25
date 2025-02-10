final class Film extends LibraryItem {
    private String title;
    private String genre;
    private String director;
    private String year;
    private int runtime;
    private String rating;

    public Film(int id, String title, String genre, String director, String year, int runtime, String rating) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.runtime = runtime;
        this.rating = rating;
    }

    @Override
    public int getLoanPeriod(boolean isStudent) {
        return 2;
    }

    @Override
    public double getOverdueFineRate() {
        return 5.0;
    }
}
