import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testDaysOverdue() {
        User student = new User("Student User", true);
        Book book = new Book(1, "Title", "Author", "Genre", "Publisher");

        book.updateStatusBorrowed(student, 1);
        assertEquals(0, book.daysOverdue(10, student));

        book.updateStatusBorrowed(student, 1);
        assertEquals(2, book.daysOverdue(17, student));
    }

    @Test
    public void testIsOverdue() {
        User student = new User("Student User", true);
        Book book = new Book(1, "Title", "Author", "Genre", "Publisher");

        book.updateStatusBorrowed(student, 1);
        assertFalse(book.isOverdue(10, student));

        assertTrue(book.isOverdue(16, student));
    }

    @Test
    public void testComputeFine() {
        User student = new User("Student User", true);
        Book book = new Book(1, "Title", "Author", "Genre", "Publisher");

        book.updateStatusBorrowed(student, 1);
        assertEquals(0.0, book.computeFine(14, student), 0.01);

        assertEquals(1.0, book.computeFine(17, student), 0.01);
    }

    @Test
    public void testShowUsersWithOverdueItems() {
        Library library = new Library();
        User student = new User("Student User", true);
        Book book = new Book(1, "Title", "Author", "Genre", "Publisher");

        library.addUser(student);
        library.borrowItem(1, student, 1, 5.0);
        assertDoesNotThrow(() -> library.showUsersWithOverdueItems(20));
    }

    @Test
    public void testCheckUserAccount() {
        Library library = new Library();
        User student = new User("Student User", true);
        Book book = new Book(1, "Title", "Author", "Genre", "Publisher");

        library.addUser(student);
        library.borrowItem(1, student, 1, 5.0);

        assertDoesNotThrow(() -> library.checkUserAccount(student, 20));
    }
}
