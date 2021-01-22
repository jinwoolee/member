package kr.or.ddit.common;

public class SearchCondition {
	
	private Integer page;
	private Integer pageSize;
	private SearchType searchType;
	private String searchKeyword;
	
	public Integer getPage() {
		return page == null ? 0 : page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize == null ? 5 : pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public SearchType getSearchType() {
		return searchType == null ? SearchType.none : searchType;
	}
	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword == null ? "" : searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	@Override
	public String toString() {
		return "SearchCondition [page=" + page + ", pageSize=" + pageSize + ", searchType=" + searchType
				+ ", searchKeyword=" + searchKeyword + "]";
	}
}
