public class Journal extends LibraryItem{
    private String eissn;
    private String publisher;
    private String latestIssue;
    private String journalURL;

    public Journal(int id, String eissn, String publisher, String latestIssue, String journalURL){
        super(id);
        this.eissn = eissn;
        this.publisher = publisher;
        this.latestIssue = latestIssue;
        this.journalURL = journalURL;
    }

    @Override
    public int getBorrowingPeriod(boolean isStudent){
        return isStudent ? 3 : 7;
    }

    @Override
    public double getFinePerDay(){
        return 2;
    }
}