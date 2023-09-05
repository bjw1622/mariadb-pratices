//package bookshop.example;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class BookShop {
//
//	public static void main(String[] args) {
//		displayBookInfo();
//		Book[] books = new Book[10];
//
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
//		
//		int num = scanner.nextInt();
//		scanner.close();
//
//		BookVo vo = new BookVo();
//		
//		vo.setNo(num);
//		vo.setRend("Y");
//		
//		new BookDao().update(vo);
//		
//		// (1) 입력된 번호에 맞는 책을 찾아 대여 되었음(상태코드=0)을 체크 합니다.
//		Book.borrow(num, books);
//		// (2) Book 객체의 정보를 출력
//		displayBookInfo(books);
//	}
//
//	private static void displayBookInfo() {
//		// TODO Auto-generated method stub
//		System.out.println("*****도서 정보 출력하기*****");
//		List<BookVo> list = new BookDao().findAll();
//	}
//}