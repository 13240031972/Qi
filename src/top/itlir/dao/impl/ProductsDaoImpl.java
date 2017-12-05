package top.itlir.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchResult;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.itlir.dao.ProductsDao;
import top.itlir.pojo.Product;
import top.itlir.pojo.Result;

@Repository
public class ProductsDaoImpl implements ProductsDao {

	// ע��solr�������
	@Autowired
	private SolrServer solrServer;

	/**
	 * DAO:���ݷ��ʲ�--���������� ����:SolrQuery ������:queryProductsWithCondition
	 */
	public Result queryProductsWithCondition(SolrQuery solrQuery) {
		// ������ҳ��װ�����
		Result result = new Result();
		
		//����list����,��װ���������ѯ��Ʒ����
		List<Product> pList = new ArrayList<Product>();

		try {
			// ���ݷ�װ���������ѯ������
			QueryResponse response = solrServer.query(solrQuery);
			// ��ȡ�ĵ�����
			SolrDocumentList results = response.getResults();
			// ��ȡ�����ܼ�¼��
			Long numFound = results.getNumFound();
			// ���ܼ�¼�����õ���ҳ��װ�����
			result.setTotalRecord(numFound.intValue());

			// ֱ��ѭ���ĵ����϶���
			for (SolrDocument docs : results) {
				
				//������Ʒ����products,��װÿһ����Ʒ�ĵ�����
				Product p = new Product();
				
				// ��ȡ�ĵ�������ֵ
				// docs.get(key)
				// id
				String id = (String) docs.get("id");
				p.setPid(id);
				// ����
				String product_name = (String) docs.get("product_name");

				// ��ȡ����
				Map<String, Map<String, List<String>>> highlighting = response
						.getHighlighting();
				// ��һ��map��key����id
				Map<String, List<String>> map = highlighting.get(id);
				// �ڶ���map��key�Ǹ����ֶ�����
				List<String> list = map.get("product_name");
				// �ж��Ƿ��и���
				if (list != null && list.size() > 0) {
					product_name = list.get(0);
				}

				p.setName(product_name);

				// �۸�
				Float product_price = (Float) docs.get("product_price");
				
				p.setPrice(product_price);
				
				// ͼƬ��ַ
				String product_picture = (String) docs.get("product_picture");
				p.setPicture(product_picture);
				
				//����Ʒ������뼯��
				pList.add(p);

			}
			
			//�Ѳ�ѯ������Ʒ��������ҳ��װ�����
			result.setpList(pList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
