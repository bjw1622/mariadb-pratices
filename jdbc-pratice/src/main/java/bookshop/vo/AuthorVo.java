package bookshop.vo;

public class AuthorVo {
	private int authorNo;
	private String name;
	public int getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(int authorNo) {
		this.authorNo = authorNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AuthorVo [authorNo=" + authorNo + ", name=" + name + "]";
	}
}
