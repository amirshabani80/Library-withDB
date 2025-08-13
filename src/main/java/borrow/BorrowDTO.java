package borrow;
import lombok.Data;
@Data
public class BorrowDTO {
    private Integer memberId;
    private Integer bookId;
    private String borrowDate;
    private String returnDate;

    public BorrowDTO(String bookId, String memberId, String borrowDate, String returnDate) {
        this.memberId = Integer.valueOf(memberId);
        this.bookId = Integer.valueOf(bookId);
        this.borrowDate = borrowDate;
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