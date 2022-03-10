package com.nit.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class NamedHQLTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			
			//get access to Named HQL select query
			Query query = ses.getNamedQuery("HQL_GET_PRODUCTS_BY_PRICE_RANGE");
			//Set values to query params
			query.setParameter("min", 1000.0f);
			query.setParameter("max", 50000.0f);
			//Execute the query
			List<Product> list = query.getResultList();
			//Process the result
			list.forEach(System.out::println);
			
			//Begin transaction
			tx = ses.beginTransaction();
			//get access to Named HQL non-select query
			Query query1 = ses.getNamedQuery("INCREASE_PRODUCT_PRICE_BY_PRICE_RANGE");
			//Set values to query params
			query1.setParameter("newValue", 2000.0f);
			query1.setParameter("range", 1000.0f);
			//Execute the query
			int count = query1.executeUpdate();
			System.out.println("No of records that are affected :: "+count);
			tx.commit();
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in executing the Named HQL query");
			}
		}
	}//main
}//class