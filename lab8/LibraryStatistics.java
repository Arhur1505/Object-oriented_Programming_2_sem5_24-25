import java.util.List;

public interface LibraryStatistics {
    default void displayLibraryStatistics(List<LibraryItem> items) {
        long bookCount = items.stream().filter(item -> item instanceof Book).count();
        long filmCount = items.stream().filter(item -> item instanceof Film).count();
        long journalCount = items.stream().filter(item -> item instanceof Journal).count();

        System.out.println("Books: " + bookCount);
        System.out.println("Films: " + filmCount);
        System.out.println("Journals: " + journalCount);
    }
}
