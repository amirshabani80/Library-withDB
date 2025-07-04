import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberService {

    private static List<MemberDTO> memberList = new ArrayList<>();

    public static void addMember() {

        Scanner scanner = new Scanner(System.in);

        MemberDTO newMember = new MemberDTO();
        System.out.println("please Enter First & Last name");
        newMember.setName(scanner.nextLine());
        System.out.println("Enter Birthday (like: 1998/11/23)");
        newMember.setBirthday(scanner.next());
        System.out.println("please enter number (like 0933++++323) ");
        newMember.setNumber(scanner.next());

        memberList.add(newMember);

        System.out.println("new member added \n Your ID:" + newMember.getId());
    }

    public static void showMembers() {
        if (MemberService.memberList.isEmpty()) {
            System.out.println("The Member List is Empty");
        } else
            for (MemberDTO s : memberList) {
                System.out.println(s.getInfo());
            }
    }

    public static void deleteMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<<Enter Member ID>>");
        int idInput = scanner.nextInt();

        MemberDTO memberDTO = findById(idInput);
        if (memberDTO != null) {
            memberList.remove(memberDTO);
            System.out.println(memberDTO.getInfo());
            System.out.println("Member with ID:" + memberDTO.getId() + " Removed");
        } else {
            System.out.println("Invalid ID!!");
        }

/**
 * method man ke pak mikard
 */
        /*for (MemberDTO s : memberList) {
            if (s.getId() == idInput) {
                memberList.remove(s);
                System.out.println(s.getInfo());
                System.out.println("Member with ID:" + s.getId() + " Removed");
                return;
            }
        }
        System.out.println("peyda nashod");*/
    }

    public void editMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter ID");
        int id = Integer.parseInt(scanner.next());

        MemberDTO memberDTO = findById(id);
        if (memberDTO != null) {

            System.out.println("Enter Member name");
            memberList.get(id).setName(scanner.next());
            System.out.println("Enter Member Birthday (like 2000/02/25)");
            memberList.get(id).setBirthday(scanner.next());
            System.out.println("Enter Member Number (like: 0912xxxx263)");
            memberList.get(id).setNumber(scanner.next());
            System.out.println(memberList.get(id).getInfo());
        } else
            System.out.println("Invalid ID!!!");
    }

    /**
     *     method id mohandes karimipoor
     */
   static MemberDTO findById(int id) {
        for (MemberDTO memberDTO : memberList) {
            if (memberDTO.getId() == id) {
                return memberDTO;
            }
        }
        return null;
    }
}
