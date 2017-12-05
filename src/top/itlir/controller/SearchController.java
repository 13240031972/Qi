package top.itlir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import top.itlir.pojo.Result;
import top.itlir.service.ProductsService;

@Controller
@RequestMapping("search")
public class SearchController {
	//ע��service�������
		@Autowired
		private ProductsService productsService;
		
		/**
		 * ����:��ת����Ʒ�����б�ҳ��product_list
		 * ����:String queryString,String catalog_name,String price,Integer page,Integer rows,String sort,Model model
		 */
		@RequestMapping("list")
		public String list(String queryString,
				String catalog_name,
				String price,
				@RequestParam(defaultValue="1") Integer page,
				@RequestParam(defaultValue="20") Integer rows,
				@RequestParam(defaultValue="1") String sort,
				Model model){
			//����service���񷽷�
			Result result = productsService.queryProductsWithCondition(queryString, catalog_name, price, page, rows, sort);
			//��������ѯ����
			model.addAttribute("queryString", queryString);
			//���
			model.addAttribute("catalog_name", catalog_name);
			//�۸�
			model.addAttribute("price", price);
			//����
			model.addAttribute("sort", sort);
			//��ҳ����
			model.addAttribute("result", result);
			return "product_list";
		}
}