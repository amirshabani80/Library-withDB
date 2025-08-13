package book;
import lombok.Data;
@Data
public class BookDTO {

    private String name;
    private String writer;
    private Integer price;
    private Status status;
    private int id;

    public BookDTO(String name, String writer, String price, String status) {
        this.name = name;
        this.writer = writer;
        this.price = Integer.parseInt(price);
        this.status = Status.valueOf(status);
    }

    public enum Status {
        AVAILABLE,
        NEEDREPAIRE,
        BORROWED
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", writer='" + writer + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", id=" + id +
                '}';
    }




}