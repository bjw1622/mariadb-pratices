package bookmall.vo;

public class Orders {
    private Long no;
    private Long memberNo;
    private String name;
    private String email;
    private int totalPrice;
    private String address;
    private String ordering;

    public Orders(String name, String email, int totalPrice, String address, String ordering) {
        this.name = name;
        this.email = email;
        this.totalPrice = totalPrice;
        this.address = address;
        this.ordering = ordering;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Long memberNo) {
        this.memberNo = memberNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }
}
