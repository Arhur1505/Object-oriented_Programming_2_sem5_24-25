abstract class LibraryItem{
    private int id;
    private boolean isBorrowed;
    private String borrowedBy;
    private int borrowedDayOfYear = -1;

    public abstract int getBorrowingPeriod(boolean isStudent);
    public abstract double getFinePerDay();

    public LibraryItem(int id) {
        this.id = id;
        this.isBorrowed = false;
        this.borrowedDayOfYear = -1;
    }

    public int daysOverdue(int currentDayOfYear, User user){
        if(!isBorrowed || borrowedDayOfYear == -1){
            return 0;
        }

        int dueDayOfYear = borrowedDayOfYear + getBorrowingPeriod(user.getIsStudent());
        
        if(currentDayOfYear <= dueDayOfYear){
            return 0;
        }

        return currentDayOfYear - dueDayOfYear;
    }

    public boolean isOverdue(int currentDayOfYear, User user){
        if(!isBorrowed || borrowedDayOfYear  == -1){
            return false;
        }

        int dueDayOfYear = borrowedDayOfYear + getBorrowingPeriod(user.getIsStudent());
        return currentDayOfYear > dueDayOfYear;
    }

    public double computeFine(int currentDayOfYear, User user){
        int overdueDays = daysOverdue(currentDayOfYear, user);
        return overdueDays * getFinePerDay();
    }

    public void updateStatusBorrowed(User user, int currentDayOfYear){
        isBorrowed = true;
        borrowedBy = user.getName();
        borrowedDayOfYear = currentDayOfYear;
    }

    public void updateStatusReturned(){
        isBorrowed = false;
        borrowedBy = null;
        borrowedDayOfYear = -1;
    }

    public int getId(){
        return id;
    }

    public String getBorrowedBy(){
        return borrowedBy;
    }

    public boolean isBorrowed(){
        return isBorrowed;
    }

    public double calculateFineForUser(int currentDayOfYear, User user) {
        if (!borrowedBy.equals(user.getName())) {
            return 0;
        }
        return computeFine(currentDayOfYear, user);
    }

}