package bookmall.vo;

public class OrdersBook {
    private Long no;
    private Long ordersNo;
    private Long bookNo;
    private String title;
    private int count;
    private int price;

    public OrdersBook(Long bookNo, String title, int count) {
        this.bookNo = bookNo;
        this.title = title;
        this.count = count;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getOrdersNo() {
        return ordersNo;
    }

    public void setOrdersNo(Long ordersNo) {
        this.ordersNo = ordersNo;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
