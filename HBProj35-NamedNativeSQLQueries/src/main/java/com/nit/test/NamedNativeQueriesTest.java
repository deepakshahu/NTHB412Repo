package com.nit.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class NamedNativeQueriesTest {

	public static void main(String[] args) {

		// Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try (factory; ses) {

			//get access to named native sql query 1
			NativeQuery query1 = ses.getNamedNativeQuery("GET_PRODUCTS_BY_PRICE_RANGE");
			//map entity query results with entity class
			query1.addEntity(Product.class);
			//set values to query param
			query1.setParameter(1, 1000.0f);
			query1.setParameter(2, 50000.0f);
			//execute the query
			List<Product> list = query1.getResultList();
			//process the result
			list.forEach(prod->{
				System.out.println(prod);
			});

			System.out.println("=====================================");
			
			//begin tx
			tx = ses.beginTransaction();
			//get access to named native sql query2
			NativeQuery query2 = ses.getNamedNativeQuery("HIKE_PRICE_BY_PROD_NAME");
			//set query param values
			query2.setParameter(1, 200.0f);
			query2.setParameter(2, "Table");
			//execute the query
			int count = query2.executeUpdate();
			System.out.println("No of records that are affected :: "+count);
			//commit the tx	
			tx.commit();
		} catch (HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				System.out.println("Problem in query execution");
				tx.rollback();
			}	
			he.printStackTrace();
		}
	}// main
}// class