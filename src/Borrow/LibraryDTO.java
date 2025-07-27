package borrow;

public class LibraryDTO {
    private int memberId;
    private int bookId;
    private String borrowDate;
    private String returnDate;

    public LibraryDTO(int bookId,int memberId,  String borrowDate, String returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "|book=" + bookId +
                "|member=" + memberId +
                "|borrowDate='" + borrowDate + '\'' +
                "|returnDate='" + returnDate + '\'';
    }
}