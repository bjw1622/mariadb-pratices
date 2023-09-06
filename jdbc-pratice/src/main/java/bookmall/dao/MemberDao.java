package bookmall.dao;

import bookmall.vo.Member;
import bookshop.vo.BookInfoVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    public void insert(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "INSERT INTO member (name, phone, email,pw)\n" +
                    "VALUES (?,?,?,?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPw());

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

    public List<Member> showMemberList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Member> memberList = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
            String sql = "select * from member";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                memberList.add(new Member(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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
        return memberList;
    }

    public Member findNameAndEmail(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String name = null;
        String email = null;
        Member member = new Member();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2:3307/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

            String sql = "select name,email from member where no = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Long.toString(no));

            pstmt.executeQuery();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");
                member.setName(name);
                member.setEmail(email);
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
        return member;
    }
}
