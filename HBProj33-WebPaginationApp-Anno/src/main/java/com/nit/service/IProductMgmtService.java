package com.nit.service;

import java.util.List;

import com.nit.entity.Product;

public interface IProductMgmtService {
	public long getPagesCount(int pageSize);
	public List<Product> getPageData(int pageNo, int pageSize);
}
