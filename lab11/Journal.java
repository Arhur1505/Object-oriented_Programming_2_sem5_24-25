final class Journal extends LibraryItem {
    private String eISSN;
    private String publisher;
    private String latestIssue;
    private String journalURL;

    public Journal(int id, String eISSN,String publisher, String latestIssue, String journalURL) {
        super(id);
        this.eISSN = eISSN;
        this.publisher = publisher;
        this.latestIssue = latestIssue;
        this.journalURL = journalURL;
    }

    @Override
    public int getLoanPeriod(boolean isStudent){
        return isStudent ? 3 : 7;
    }

    @Override
    public double getOverdueFineRate() {
        return 2.0;
    }
}