package top.itlir.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.itlir.dao.ProductsDao;
import top.itlir.pojo.Result;
import top.itlir.service.ProductsService;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

	//ע��dao
		@Autowired
		private ProductsDao productsDao;

		
		/**
		 * SERVICE:ҵ��� ---������װ--��ҳ--����
		 * ����:����������ѯ������
		 * ����:String queryString,String catalog_name,String price,Integer page,Integer rows,String sort
		 * @return
		 */
		public Result queryProductsWithCondition(String queryString,
				String catalog_name, String price, Integer page, Integer rows,
				String sort) {
			
			//����SolrQuery,��װ���в�ѯ����
			SolrQuery solrQuery = new SolrQuery();
			
			// �жϲ�ѯ�����Ƿ�Ϊ��,�����ǿ��ַ���
			if(queryString!=null && !"".equals(queryString)){
				solrQuery.setQuery(queryString);
			}else{
				//��ѯ����
				solrQuery.setQuery("*:*");
			}
			
			//������˲�ѯ
			if(catalog_name!=null && !"".equals(catalog_name)){
				solrQuery.addFilterQuery("product_catalog_name:"+catalog_name);
			}
			//�۸���� -- 0-9,10-19,20-29   50-*
			if(price!=null && !"".equals(price)){
				//�и�۸�
				String[] prices = price.split("-");
				//���˼۸�
				solrQuery.addFilterQuery("product_price:["+prices[0]+" TO "+prices[1]+"]");
			}
			
			//��ҳ����
			//������ʼҳ
			int startNo = (page-1)*rows;
			solrQuery.setStart(startNo);
			solrQuery.setRows(rows);
			
			//����
			if("1".equals(sort)){
				solrQuery.setSort("product_price", ORDER.asc);
			}else{
				solrQuery.setSort("product_price", ORDER.desc);
			}
			
			//��������
			//��������
			solrQuery.setHighlight(true);
			//���ø����ֶ�
			solrQuery.addHighlightField("product_name");
			//���ø���ǰ׺
			solrQuery.setHighlightSimplePre("<font color='red'>");
			//��׺
			solrQuery.setHighlightSimplePost("</font>");
			
			//����Ĭ����
			solrQuery.set("df", "product_keywords");
			
			//����dao��ѯ
			Result result = productsDao.queryProductsWithCondition(solrQuery);
			//���õ�ǰҳֵ����ҳ��װ�����
			result.setCurPage(page);
			//������ҳ��
			Integer count = result.getTotalRecord();
			int pages = count/rows;
			if(count%rows>0){
				pages++;
			}
			//����ҳ�����÷�ҳ��װ�����
			result.setTotalPages(pages);
			
			return result;
		}
}
