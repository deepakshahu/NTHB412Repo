package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class SaveObjectTestInBatch {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();

		//Get Session Object
		Session ses = HibernateUtil.getSession();

		Transaction tx = null;
		try(factory;ses){  //java9 TWR
			for(int i=1; i<=5; i++) {
				//Begin Tx
				tx = ses.beginTransaction();

				//Prepare object for entity class
				Product prod = new Product();
				prod.setPname("Desk");
				prod.setPrice(8000.5f);
				prod.setQty(2.0f);
				//instruction to save object
				String idVal = (String) ses.save(prod);
				System.out.println("The generated id value is :: "+idVal);

				//commit the tx
				tx.commit();
				System.out.println("Object is saved");
			}
		}
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved");
			}
			he.printStackTrace();
		}
	}//main
}//class