package bookshop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bookshop.example.Book;
import bookshop.vo.AuthorVo;
import bookshop.vo.BookInfoVo;
import bookshop.vo.BookVo;

public class BookDao {

    public void updateRent(BookVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1. JDBC Driver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            //2. 연결하기
            String url = "jdbc:mariadb://192.168.64.2:3307/webdb?charset=utf8";
            conn = DriverManager.getConnection(url, "webdb", "qorwodnjs1!");

            int no = vo.getNo();
            String rent = vo.getRent();
            //3. SQL 준비
            String sql = "update book set rent = ? where no = ?";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setString(1, rent);
            pstmt.setInt(2, no);

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

    public List<BookInfoVo> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookInfoVo> bookInfos = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/webdb?charset=utf8";
            conn = DriverManager.getConnection(url, "webdb", "qorwodnjs1!");
            String sql = "SELECT * FROM author AS a\n" +
                    "INNER JOIN book AS b ON a.no = b.author_no;\n";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bookInfos.add(new BookInfoVo(rs.getString(4), rs.getString(2), rs.getString(5)));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if(rs != null){
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
        return bookInfos;
    }
}
