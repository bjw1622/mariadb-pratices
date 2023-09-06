package bookmall.dao;

import bookmall.vo.Category;
import bookmall.vo.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public List<Category> showCategory() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Category> categoryList = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
            String sql = "select * from category";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                categoryList.add(new Category(rs.getString(2)));
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
        return categoryList;
    }

    public void insert(Category category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "insert into category(name) values(?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, category.getName());

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
}
