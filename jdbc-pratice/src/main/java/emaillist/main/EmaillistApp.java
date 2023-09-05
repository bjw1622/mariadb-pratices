package emaillist.main;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

import java.util.List;
import java.util.Scanner;

public class EmaillistApp {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println(" (l)ist (i)nsert (d)elete (q)uit > ");
            String command = scanner.nextLine();
            if ("l".equals(command)) {
                doList();
            } else if ("i".equals(command)) {
                doInsert();
            } else if ("d".equals(command)) {
                doDelete();
            } else if ("q".equals(command)) {
                break;
            }
        }
    }

    private static void doDelete() {
        System.out.println("이메일을 입력하세요");
        String email = scanner.nextLine();
        new EmaillistDao().deleteByEmail(email);

        doList();
    }

    private static void doInsert() {
        System.out.println("성:");
        String firstName = scanner.nextLine();

        System.out.println("이름:");
        String lastName = scanner.nextLine();


        System.out.println("이메일:");
        String email = scanner.nextLine();

        EmaillistVo vo = new EmaillistVo();
        vo.setFirstName(firstName);
        vo.setLastName(lastName);
        vo.setEmail(email);

        new EmaillistDao().insert(vo);

    }

    private static void doList() {
        List<EmaillistVo> list = new EmaillistDao().findAll();
        for (EmaillistVo vo : list){
            System.out.println("이름:" + vo.getFirstName() + vo.getLastName() + ", 이메일:" + vo.getEmail());
        }
    }
}
