public class LibraryDTO {
private MemberDTO member;
private BookDTO book;
private String borrowDate;
private String returnDate;

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

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public BookDTO getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "LibraryDTO{" +
                "member=" + member +
                ", book=" + book +
                ", borrowDate='" + borrowDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
