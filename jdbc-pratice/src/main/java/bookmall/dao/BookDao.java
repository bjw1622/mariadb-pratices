package bookmall.dao;

import bookmall.vo.Book;
import bookmall.vo.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public void insert(Book book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "insert into book(title,price,category_no) values(?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getTitle());
            pstmt.setInt(2, book.getPrice());
            pstmt.setLong(3, book.getCategoryNo());

            //5. SQL 실행
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

    public List<Book> showBook() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Book> bookList = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
            String sql = "select * from book";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bookList.add(new Book(rs.getString(2), rs.getInt(3), rs.getLong(4)));
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
        return bookList;
    }

    public String findBookTitle(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String name = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "select title from book where no = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Long.toString(no));

            pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("title");
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
        return name;
    }

    public int findBookPrice(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int price = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "select price from book where no = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Long.toString(no));

            pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                price = rs.getInt("price");
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
        return price;
    }
}
