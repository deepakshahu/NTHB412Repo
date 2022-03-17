package com.nit.dao;

import java.util.List;

import com.nit.entity.Product;

public interface IProductDAO {
	public long getTotalRecordsCount();
	public List<Product> getPageData(int startPosition, int pageSize);
}
