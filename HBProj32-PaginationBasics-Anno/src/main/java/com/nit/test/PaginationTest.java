package com.nit.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class PaginationTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			
			//Create HQL query
			Query query = ses.createQuery("from Product");
			query.setFirstResult(0);  //start pos
			query.setMaxResults(3);  //page size
			//Execute the query
			List<Product> list = query.getResultList();
			//Process the result
			list.forEach(System.out::println);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class