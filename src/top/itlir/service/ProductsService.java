package top.itlir.service;

import top.itlir.pojo.Result;

public interface ProductsService {

	/**
	 * SERVICE:ҵ��� ---������װ--��ҳ--����
	 * ����:����������ѯ������
	 * ����:String queryString,String catalog_name,String price,Integer page,Integer rows,String sort
	 * @return
	 */
	public Result queryProductsWithCondition(String queryString,
			String catalog_name,
			String price,
			Integer page,
			Integer rows,
			String sort);

}
