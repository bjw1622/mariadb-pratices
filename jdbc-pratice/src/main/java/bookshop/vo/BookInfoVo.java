package bookshop.vo;

public class BookInfoVo {
    private String title;
    private String authorName;

    private String rent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "BookInfoVo{" +
                "title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", rent='" + rent + '\'' +
                '}';
    }

    public BookInfoVo(String title, String authorName, String rent) {
        this.title = title;
        this.authorName = authorName;
        this.rent = rent;
    }
}
