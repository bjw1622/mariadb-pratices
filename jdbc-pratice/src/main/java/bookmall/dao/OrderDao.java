package bookmall.dao;

import bookmall.vo.Member;
import bookmall.vo.Orders;
import bookmall.vo.OrdersBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public void insertOrders(Long memberNo, String address, String ordering) {
        Member member = new MemberDao().findNameAndEmail(memberNo);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "INSERT INTO orders (name, email, total_price,address,member_no,ordering)\n" +
                    "VALUES (?,?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setInt(3, 0);
            pstmt.setString(4, address);
            pstmt.setLong(5, memberNo);
            pstmt.setString(6, ordering);
            pstmt.executeQuery();

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertOrdersBook(Long orderNo, Long bookNo, int count) {
        BookDao bookDao = new BookDao();
        int price = bookDao.findBookPrice(bookNo);
        String title = bookDao.findBookTitle(bookNo);

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "INSERT INTO orders_book (title, count, price,orders_no,book_no)\n" +
                    "VALUES (?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, title);
            pstmt.setInt(2, count);
            pstmt.setInt(3, price * count);
            pstmt.setLong(4, orderNo);
            pstmt.setLong(5, bookNo);
            pstmt.executeQuery();

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Orders> showOrders() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Orders> ordersList = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
            String sql = "select * from orders";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Long no = rs.getLong(1);
                int totalPrice = sumOrdersPrice(no);
                ordersList.add(new Orders(rs.getString(2), rs.getString(3), totalPrice, rs.getString(5), rs.getString(7)));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ordersList;
    }

    // 총액 구하는거 및 업데이트
    public int sumOrdersPrice(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalPrice = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
            String sql = "select sum(price) from orders_book where orders_no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                totalPrice = rs.getInt(1);
                sumUpdateOrders(no, totalPrice);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalPrice;
    }

    public void sumUpdateOrders(Long no, int totalPrice) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
            String sql = "update orders set total_price = ? where no = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, totalPrice);
            pstmt.setLong(2, no);
            pstmt.executeQuery();
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<OrdersBook> showOrdersBook() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrdersBook> ordersBookList = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
            String sql = "select * from orders_book";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersBookList.add(new OrdersBook(rs.getLong(6),rs.getString(2),rs.getInt(3)));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ordersBookList;
    }
}
