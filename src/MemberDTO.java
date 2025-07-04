public class MemberDTO {
    private String name;
    private String birthday;
    private String number;
    private int id;
    private static int count;

    MemberDTO() {
        id = ++count;
    }

    public MemberDTO(String name, String birthday, String number) {
        this.name = name;
        this.birthday = birthday;
        this.number = number;
        this.id = ++count;
    }

    public String getInfo() {
        return "MemberDTO{ " +
                "name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", number=' " + number + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }
}
