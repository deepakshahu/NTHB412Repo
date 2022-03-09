package com.nit.test;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.utility.HibernateUtil;

public class HQLInsertTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			tx = ses.beginTransaction();
			
			Query query = ses.createQuery("INSERT INTO SpecialProduct(pid,pname,price,qty) SELECT p.pid,p.pname,p.price,p.qty from Product as p WHERE p.price>=:val");
			//set param values
			query.setParameter("val", 500.0f);
			
			//Execute the query
			int count = query.executeUpdate();
			tx.commit();
			System.out.println("No of records that are copied :: "+count);
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in executing query");
			}
		}
	}//main
}//class