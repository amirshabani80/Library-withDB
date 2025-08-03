package book;


public class BookDTO {

    private String name;
    private String writer;
    private int price;
    private Status status;
    private int id;

    public BookDTO(String name, String writer, String price, String status) {
        this.name = name;
        this.writer = writer;
        this.price = price;
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

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}