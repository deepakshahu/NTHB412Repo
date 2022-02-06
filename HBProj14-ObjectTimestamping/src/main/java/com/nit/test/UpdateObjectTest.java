package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Stock;
import com.nit.utility.HibernateUtil;

public class UpdateObjectTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//Prepare object
			Stock stock = ses.get(Stock.class, 1);
			if(stock==null) {
				System.out.println("Stock not found");
				return;
			}
			else {
				//begin transaction
				tx = ses.beginTransaction();
				//Update object
				stock.setPrice(251.45d);
				ses.update(stock);
				tx.commit();
				System.out.println("Object/record is lastly updated at :: "+stock.getLastUpdated());				
			}//else
		}//try
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null  || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not updated");
			}//if
			he.printStackTrace();
		}//catch
	}//main
}//class