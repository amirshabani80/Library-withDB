package member;
import lombok.Data;
@Data
public class MemberDTO {
    private String name;
    private String birthday;
    private String phoneNumber;
    private Integer id;


    public MemberDTO(String name, String birthday, String phoneNumber) {
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "name=" + name +
                ", birthday=" + birthday +
                ", phoneNumber=" + phoneNumber +
                ", id=" + id +
                '}';
    }


}
