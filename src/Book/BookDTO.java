package Book;

public class BookDTO {

    private String name;
    private String writer;
    private int price;
    private Status status;
    private int id;


    public enum Status {
        AVAILABLE,
        NEEDREPARE,
        BORROW
    }

    public String getInfo() {
        return "book.BookDTO{" +
                "name='" + name + '\'' +
                ", writer='" + writer + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    public BookDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}