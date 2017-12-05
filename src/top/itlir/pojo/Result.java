package top.itlir.pojo;

import java.util.List;

public class Result {
	//当前页
		private Integer curPage;
		//总页码
		private Integer totalPages;
		//总记录数
		private Integer totalRecord;
		//总记录
		private List<Product> pList;
		public Integer getCurPage() {
			return curPage;
		}
		public void setCurPage(Integer curPage) {
			this.curPage = curPage;
		}
		public Integer getTotalPages() {
			return totalPages;
		}
		public void setTotalPages(Integer totalPages) {
			this.totalPages = totalPages;
		}
		public Integer getTotalRecord() {
			return totalRecord;
		}
		public void setTotalRecord(Integer totalRecord) {
			this.totalRecord = totalRecord;
		}
		public List<Product> getpList() {
			return pList;
		}
		public void setpList(List<Product> pList) {
			this.pList = pList;
		}
		
		
}
