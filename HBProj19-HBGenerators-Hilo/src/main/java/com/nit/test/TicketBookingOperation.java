package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class TicketBookingOperation implements Runnable {

	@Override
	public void run() {
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try{
			//Begin transaction
			tx = ses.beginTransaction();
			Product prod = new Product();
			prod.setPname("Spoon");
			prod.setPrice(900.0f);
			prod.setQty(6.0f);
			//Instruction to save the object
			Integer idVal = (Integer) ses.save(prod);
			System.out.println("Generated id value :: "+idVal);
			tx.commit();
			System.out.println("Object is saved");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			tx.rollback();
			System.out.println("Object is not saved");
		}
		finally {
			ses.close();
		}//finally
	}//run
}//class