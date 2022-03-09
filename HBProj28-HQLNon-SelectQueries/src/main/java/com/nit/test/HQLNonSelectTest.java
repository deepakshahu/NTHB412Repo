package com.nit.test;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.utility.HibernateUtil;

public class HQLNonSelectTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			tx = ses.beginTransaction();
			
			/*Query query = ses.createQuery("update from Product set price = price-:discount where price >= :min and price <= :max");
			//set param values
			query.setParameter("discount", 3000.0f);
			query.setParameter("min", 1000.0f);
			query.setParameter("max", 20000.0f);*/
			
			Query query = ses.createQuery("delete from Product where qty<=:val");
			//Set param values
			query.setParameter("val", 1.0f);
			
			//Execute the query
			int count = query.executeUpdate();
			tx.commit();
			System.out.println("No of records that are affected :: "+count);
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