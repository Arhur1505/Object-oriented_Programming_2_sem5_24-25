public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.loadBooks("books.csv");
        library.loadFilms("movies.csv");
        library.loadJournals("jlist.csv");

        User student1 = new User("Sergio Perez", true);
        User student2 = new User("Max Verstappen", true);
        User teacher = new User("Ayrton Senna", false);

        library.addUser(student1);
        library.addUser(student2);
        library.addUser(teacher);

        library.borrowItem(1, student1, 1, 5);
        library.borrowItem(2, student2, 2, 5);
        library.borrowItem(2, student1, 3, 5);
        library.borrowItem(5, student1, 4, 5);
        library.borrowItem(6, student2, 5, 5);
        library.borrowItem(15, teacher, 1, 5);
        library.borrowItem(13, teacher, 5, 5);
        library.borrowItem(11, teacher, 5, 5);

        library.checkUserAccount(student1, 5);
        library.checkUserAccount(student2, 250);
        library.checkUserAccount(teacher, 20);
        library.showUsersWithOverdueItems(3);
    }
}
