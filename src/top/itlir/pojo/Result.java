package top.itlir.pojo;

import java.util.List;

public class Result {
	//��ǰҳ
		private Integer curPage;
		//��ҳ��
		private Integer totalPages;
		//�ܼ�¼��
		private Integer totalRecord;
		//�ܼ�¼
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
