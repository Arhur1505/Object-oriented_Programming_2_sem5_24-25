import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class Library implements LibraryStatistics{
    private List<LibraryItem> items;
    private List<User> users;

    public Library() {
        items = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<LibraryItem> getItems() {
        return items;
    }    

    public void checkUserAccount(User user, int currentDayOfYear){
        double totalFine = 0;
        System.out.println(user.getName() + " account info:");

        for(LibraryItem item : items){
            if(item.isBorrowed() && item.getBorrowedBy().equals(user.getName())){
                boolean isOverdue = item.isOverdue(currentDayOfYear, user);
                double fine = item.calculateFineForUser(currentDayOfYear, user);

                System.out.println("item: " + item.getId() + ", overdue: " + isOverdue + ", fine: " + fine);
                totalFine += fine;
            }
        }

        System.out.println("total fine: " + totalFine);
    }

    public void showUsersWithOverdueItems(int currentDayOfYear){
        System.out.println("users with overdues:");
        for(User user : users){
            boolean hasOverdueItems = false;

            for(LibraryItem item : items){
                if(item.isBorrowed() && item.getBorrowedBy().equals(user.getName()) && item.isOverdue(currentDayOfYear, user)){
                    hasOverdueItems = true;
                    break;
                }
            }

            if(hasOverdueItems){
                System.out.println("- " + user.getName());
            }
        }
    }

    private int countBorrowedItemsByType(User user, String itemType){
        int count = 0;

        for(LibraryItem item : items){
            if(item.isBorrowed() && item.getBorrowedBy().equals(user.getName())){
                if(itemType.equals("Book") && item instanceof Book){
                    count++;
                }else if(itemType.equals("Film") && item instanceof Film){
                    count++;
                }else if(itemType.equals("Journal") && item instanceof Journal){
                    count++;
                }
            }
        }

        return count;
    }

    private boolean canBorrow(User user, LibraryItem item) {
        if (!user.getIsStudent()) {
            return true;
        }

        if(item instanceof Film && countBorrowedItemsByType(user, "Film") >= 1){
            return false;
        }
        if(item instanceof Book && countBorrowedItemsByType(user, "Book") >= 3){
            return false;
        }
        if(item instanceof Journal && countBorrowedItemsByType(user, "Journal") >= 3){
            return false;
        }

        return true;
    }

    public void borrowItem(int itemId, User user, int currentDayOfYear, double fineThreshold) {
        double totalFine = calculateTotalFineForUser(user, currentDayOfYear);

        if(totalFine > fineThreshold){
            System.out.println("user " + user.getName() + " has too high a fine and cannot borrow an item.");
            return;
        }

        LibraryItem item = findItemById(itemId);

        if(item == null){
            System.out.println("item id: " + itemId + " doesn't exist.");
            return;
        }

        if(item.isBorrowed()){
            System.out.println("item id: " + itemId + " has been already borrowed.");
            return;
        }

        if(!canBorrow(user, item)){
            System.out.println("user " + user.getName() + " has reached the limit of borrowed items for this type.");
            return;
        }

        item.updateStatusBorrowed(user, currentDayOfYear);
        System.out.println("item id: " + itemId + ", borrowed by " + user.getName() + ", date " + currentDayOfYear + ".");
    }

    private LibraryItem findItemById(int id){
        for(LibraryItem item : items){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    private double calculateTotalFineForUser(User user, int currentDayOfYear){
        double totalFine = 0;

        for(LibraryItem item : items){
            if (item.isBorrowed() && item.getBorrowedBy().equals(user.getName())){
                totalFine += item.calculateFineForUser(currentDayOfYear, user);
            }
        }

        return totalFine;
    }

    public void loadBooks(String filename) {
        Runnable loadBooksTask = new Runnable() {
            @Override
            public void run() {
                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    String line;
                    reader.readLine();
    
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(";", -1);
                        if (data.length < 5) {
                            continue;
                        }
    
                        int id = items.size() + 1;
                        String title = data[0];
                        String author = data[1];
                        String genre = data[2];
                        String publisher = data[4];
    
                        items.add(new Book(id, title, author, genre, publisher));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        loadBooksTask.run();
    }
    
    public void loadFilms(String filename) {
        Runnable loadFilmsTask = new Runnable() {
            @Override
            public void run() {
                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    String line;
                    reader.readLine();
    
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(";", -1);
                        if (data.length < 10) {
                            continue;
                        }
    
                        int id = items.size() + 1;
                        String title = data[1];
                        String genre = data[2];
                        String director = data[4];
                        String year = data[6];
                        int runtime = Integer.parseInt(data[7]);
                        String rating = data[8];
    
                        items.add(new Film(id, title, genre, director, year, runtime, rating));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        loadFilmsTask.run();
    }
    
    public void loadJournals(String filename) {
        Runnable loadJournalsTask = new Runnable() {
            @Override
            public void run() {
                try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                    String line;
                    reader.readLine();
    
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(";", -1);
                        if (data.length < 13) {
                            continue;
                        }
    
                        int id = items.size() + 1;
                        String eissn = data[3];
                        String publisher = data[4];
                        String latestIssue = data[6];
                        String journalURL = data[12];
    
                        items.add(new Journal(id, eissn, publisher, latestIssue, journalURL));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        loadJournalsTask.run();
    }
}