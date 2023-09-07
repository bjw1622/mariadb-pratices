package bookmall.main;

import bookmall.dao.*;
import bookmall.vo.*;

import java.util.List;

public class BookMall {

    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao();
        memberDao.insert(new Member("백재원", "01055760733", "bjw1622@gmail.com", "1234"));
        memberDao.insert(new Member("안대혁", "01077777777", "7777@gmail.com", "7777"));

        System.out.println("## 회원리스트");
        List<Member> memberList = memberDao.showMemberList();
        for (Member member : memberList) {
            System.out.println("이름 : " + member.getName() + ", 전화번호 : " + member.getPhone() + ", 이메일 : " + member.getEmail() + ", 비밀번호 : " + member.getPw());
        }

        System.out.println();
        System.out.println("## 카테고리");
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.insert(new Category("소설"));
        categoryDao.insert(new Category("수필"));
        categoryDao.insert(new Category("인문"));

        List<Category> categoryList = categoryDao.showCategory();
        for (Category category : categoryList) {
            System.out.println("카테고리 : " + category.getName());
        }

        System.out.println();
        System.out.println("## 상품");
        BookDao bookDao = new BookDao();
        bookDao.insert(new Book("자바의 정석", 3000, 1L));
        bookDao.insert(new Book("수학의 정석", 10000, 2L));
        List<Book> bookList = bookDao.showBook();
        for (Book book : bookList) {
            System.out.println("제목 : " + book.getTitle() + ", 가격 : " + book.getPrice());
        }

        System.out.println();
        System.out.println("## 카트");
        CartDao cartDao = new CartDao();
        cartDao.insert(1L, 1L, 2);
        cartDao.insert(2L, 2L, 5);

        List<Cart> cartList = cartDao.showCart();
        for (Cart cart : cartList
        ) {
            System.out.println("도서 제목 : " + bookDao.findBookTitle(cart.getBookNo()) + ", 수량 : " + cart.getCount() + ", 가격 : " + cart.getCount() * bookDao.findBookPrice(cart.getBookNo()));
        }

        // 출력 : 주문번호, 주문자(이름/이메일), 결제금액, 배송지
        // 실제 테이블 : member_no,name,email,total_price,address, ordering
        OrderDao orderDao = new OrderDao();
        orderDao.insertOrders(2L, "강남구", "20230905-02");

        // 주문도서
        // 도서번호, 도서제목, 수량
        // 실제 테이블 : order_no, book_no, title, count, price
        orderDao.insertOrdersBook(1L, 1L, 2);
        orderDao.insertOrdersBook(1L, 2L, 1);



        System.out.println();
        System.out.println("## 주문");
        List<Orders> ordersList = orderDao.showOrders();
        for (Orders orders : ordersList) {
            System.out.println("주문번호 : " + orders.getOrdering() + ", 주문자 이름 : " + orders.getName() + ", 주문자 이메일 : " + orders.getEmail() + ", 결제 금액 : " + orders.getTotalPrice() + ", 배송지 : " + orders.getAddress());
        }

        System.out.println();
        System.out.println("## 주문도서");
        List<OrdersBook> ordersBooks = orderDao.showOrdersBook();
        for (OrdersBook ordersBook : ordersBooks) {
            System.out.println("도서 번호 : " + ordersBook.getBookNo() + ", 도서 제목 : " + ordersBook.getTitle() + ", 수량 : " + ordersBook.getCount());
        }
    }
}