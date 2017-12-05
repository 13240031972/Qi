package top.itlir.dao;


import org.apache.solr.client.solrj.SolrQuery;

import top.itlir.pojo.Result;

public interface ProductsDao {

	Result queryProductsWithCondition(SolrQuery solrQuery);

}
