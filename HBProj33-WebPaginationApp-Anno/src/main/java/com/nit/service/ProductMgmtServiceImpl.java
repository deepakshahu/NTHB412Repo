package com.nit.service;

import java.util.List;

import com.nit.dao.IProductDAO;
import com.nit.dao.ProductDAOImpl;
import com.nit.entity.Product;

public class ProductMgmtServiceImpl implements IProductMgmtService {
	
	private IProductDAO dao;
	
	public ProductMgmtServiceImpl() {
		dao = new ProductDAOImpl();
	}

	@Override
	public long getPagesCount(int pageSize) {
		//get total no of records
		long recordsCount = dao.getTotalRecordsCount();
		//get PagesCount
		long pagesCount = recordsCount/pageSize;
		if(recordsCount%pageSize!=0)
			pagesCount++;
		return pagesCount;
	}
	
	@Override
	public List<Product> getPageData(int pageNo, int pageSize) {
		//find out startPosition for pagination
		int startPosition = (pageNo*pageSize)-pageSize;
		//Use DAO to get the given page data
		List<Product> list = dao.getPageData(startPosition, pageSize);
		return list;
	}

}
