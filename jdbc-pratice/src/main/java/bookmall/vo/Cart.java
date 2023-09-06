package bookmall.vo;

public class Cart {
    private Long no;
    private int count;
    private Long memberNo;
    private Long bookNo;

    public Cart(int count, Long memberNo, Long bookNo) {
        this.count = count;
        this.memberNo = memberNo;
        this.bookNo = bookNo;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Long memberNo) {
        this.memberNo = memberNo;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }
}
