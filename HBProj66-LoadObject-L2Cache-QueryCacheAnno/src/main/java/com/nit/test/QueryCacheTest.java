package com.nit.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class QueryCacheTest {

	public static void main(String[] args) {
		//get SessionFactory and Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			Query query = ses.createQuery("from Product");
			query.setCacheable(true);
			query.setCacheRegion("region1");
			List<Product> list = query.getResultList();
			list.forEach(prod->{
				System.out.println(prod);
			});
			System.out.println("----------------------------");
			List<Product> list1 = query.getResultList();
			list1.forEach(prod->{
				System.out.println(prod);
			});
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
		
	}//main
}//class