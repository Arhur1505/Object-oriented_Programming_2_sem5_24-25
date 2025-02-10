import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryItemTest {

    @Test
    void testDaysOverdue() {
        User user = new User(true, "Student");
        Film film = new Film(1, "Guardians of the Galaxy", "Action,Adventure,Sci-Fi", "James Gunn", "2014", 121, "8.1");

        film.updateStatusBorrowed(user, 1);

        int daysOverdue = film.daysOverdue(5, user);
        assertEquals(2, daysOverdue);
    }

    @Test
    void testIsOverdue() {
        User user = new User(true, "Student");
        Book book = new Book(2, "Data Smart", "Foreman, John", "data_science", "Wiley");

        book.updateStatusBorrowed(user, 30);

        boolean overdue = book.isOverdue(40, user);
        assertFalse(overdue);

        overdue = book.isOverdue(60, user);
        assertTrue(overdue);
    }

    @Test
    void testComputeFine() {
        User user = new User(false, "Teacher");
        Journal journal = new Journal(3, "2190-5738", "Springer", "v.12(12):Dec 2022", "http://www.ncbi.nlm.nih.gov/pmc/journals/1811/");

        journal.updateStatusBorrowed(user, 1);

        double fine = journal.computeFine(9, user);
        assertEquals(2.0, fine);
    }
}
