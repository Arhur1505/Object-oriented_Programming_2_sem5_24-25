abstract sealed class LibraryItem permits Book, Journal, Film{
    private int id;
    private boolean isBorrowed;
    private String borrowedBy;
    private int borrowedOn = -1;

    public abstract int getLoanPeriod(boolean isStudent);

    public abstract double getOverdueFineRate();

    public LibraryItem(int id) {
        this.id = id;
        this.isBorrowed = false;
        this.borrowedOn = -1;
    }

    public int getId() {
        return id;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public int daysOverdue(int currentDay, User user) {
        if (!isBorrowed || borrowedOn == -1) {
            return 0;
        }

        int dueDay = borrowedOn + getLoanPeriod(user.getIsStudent());

        if (currentDay <= dueDay) {
            return 0;
        }

        return currentDay - dueDay;
    }

    public boolean isOverdue(int currentDay, User user) {
        if (!isBorrowed || borrowedOn == -1) {
            return false;
        }
        int dueDay = borrowedOn + getLoanPeriod(user.getIsStudent());
        return currentDay > dueDay;
    }
    

    public double computeFine(int currentDay, User user) {
        int overdueDays = daysOverdue(currentDay, user);
        return overdueDays * getOverdueFineRate();
    }

    public void updateStatusBorrowed(User user, int currentDay) {
        isBorrowed = true;
        borrowedBy = user.getName();
        borrowedOn = currentDay;
    }

    public void updateStatusReturned() {
        isBorrowed = false;
        borrowedBy = null;
        borrowedOn = -1;
    }

    public double calculateFineForUser(int currentDay, User user) {
        if (!borrowedBy.equals(user.getName())) {
            return 0;
        }
        return computeFine(currentDay, user);
    }
}