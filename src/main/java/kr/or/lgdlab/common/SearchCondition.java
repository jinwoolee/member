package kr.or.lgdlab.common;
/**   
 *  Description : 검색 조건
 *  
 *  수정일       수정자       수정내용 
 *  ---------   ---------   ------------------------------- 
 *  2021-08-11  ljw         최초생성
 *  
 *  Copyright (C) by LG Discovery Lab. All right reserved. 
 */
public class SearchCondition {
	
	private Integer page;
	private Integer pageSize;
	private SearchType searchType;
	private String searchKeyword;
	
	public SearchCondition() {}
	
	public SearchCondition(Integer page, Integer pageSize, SearchType searchType, String searchKeyword) {
		setPage(page);
		setPageSize(pageSize);
		setSearchType(searchType);
		setSearchKeyword(searchKeyword);
	}
	
	public SearchCondition(Integer page, Integer pageSize, SearchType searchType) {
		this(page, pageSize, searchType, null);
	}

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
