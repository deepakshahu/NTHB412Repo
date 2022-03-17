package com.nit.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class ProductDAOImpl implements IProductDAO {

	@Override
	public long getTotalRecordsCount() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			//prepare and execute HQL select query
			Query query = ses.createQuery("select count(*) from Product");
			long count = (long) query.getSingleResult();
			return count;
		}
		catch(HibernateException he) {
			he.printStackTrace();
			throw he;
		}
	}//method

	@Override
	public List<Product> getPageData(int startPosition, int pageSize) {
		Session ses = HibernateUtil.getSession();
		try(ses){
			//prepare and execute HQL select query
			Query query = ses.createQuery("from Product");
			//Set pagination params
			query.setFirstResult(startPosition);
			query.setMaxResults(pageSize);
			//Execute the query
			List<Product> list = query.getResultList();
			return list;
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
			throw he;
		}
	}//method

}//class