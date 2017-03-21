package ol.entity;

public class LeanQueryModel {
	private String keyword; /* 关键词 */ 
	private String type; /* 科目 */ 
	
	 /**当前页码 */
	private int currentpage;
		
	private int firstResult;
		
	private int maxResutl;
		
	private String orderBy;
		
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResutl() {
		return maxResutl;
	}

	public void setMaxResutl(int maxResutl) {
		this.maxResutl = maxResutl;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
