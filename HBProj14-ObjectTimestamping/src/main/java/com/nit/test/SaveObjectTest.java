package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Stock;
import com.nit.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//begin transaction
			tx = ses.beginTransaction();
			//Prepare object
			Stock stock = new Stock("Tata Power", 255.67, "NSE");
			//Save object
			int idVal = (Integer) ses.save(stock);
			tx.commit();
			System.out.println("Object/record is saved with the id value :: "+idVal);
			System.out.println("Object/record is saved at :: "+stock.getLastUpdated());
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null  || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved");
			}//if
			he.printStackTrace();
		}//catch
	}//main
}//class