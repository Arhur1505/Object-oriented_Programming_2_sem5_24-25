public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.loadBooks("books.csv");
        library.loadFilms("movies.csv");
        library.loadJournals("jlist.csv");

        User student = new User(true, "Emily Clarke");
        User teacher = new User(false, "John Brown");
        library.addUser(student);
        library.addUser(teacher);

        library.borrowItem(1, student, 1, 5);
        library.borrowItem(2, student, 2, 5);
        library.borrowItem(2, student, 10, 5);
        library.borrowItem(4, student, 10, 50);
        library.borrowItem(1000, student, 1, 50);
        library.borrowItem(5, student, 30, 5);
        library.borrowItem(60000, student, 56, 100);

        library.borrowItem(200, teacher, 1, 5);
        library.borrowItem(201, teacher, 5, 5);
        library.borrowItem(202, teacher, 5, 5);
        library.borrowItem(203, teacher, 5, 5);

        library.checkUserAccount(student, 20);
        library.checkUserAccount(teacher, 16);
        library.showUsersWithOverdueItems(30);
    }
}
