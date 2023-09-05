package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookInfoVo;
import bookshop.vo.BookVo;

public class BookShop {
    public static void main(String[] args) {
        displayBookInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
        int no = scanner.nextInt();
        scanner.close();

        BookVo vo = new BookVo();
        vo.setNo(no);
        vo.setRent("Y");

        new BookDao().updateRent(vo);

        displayBookInfo();
    }

    private static void displayBookInfo() {
        System.out.println("*****도서 정보 출력하기******");
        List<BookInfoVo> list = new BookDao().findAll();
        for (BookInfoVo bookInfos : list) {
            System.out.println("책 제목: " + bookInfos.getTitle() + ",  작가: " + bookInfos.getAuthorName() + ", 대여 유무 : " + bookInfos.getRent());
        }
    }
}