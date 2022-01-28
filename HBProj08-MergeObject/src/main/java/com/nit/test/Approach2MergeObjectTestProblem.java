package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class Approach2MergeObjectTestProblem {

	public static void main(String[] args) {
		//get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		//This example will work with normal try catch block and not with TWR
		try{
			//Begin Transaction
			tx = ses.beginTransaction();
			//Load object operation
			Product prod = ses.get(Product.class, 1015);  //Loads product class object(1015) and puts in L1 cache 
			System.out.println(prod);
			//Update Object operation
			Product prod1 = new Product();
			prod1.setPid(1015);
			prod1.setPname("Bathroom Mirror");
			prod1.setPrice(416.23f);
			prod1.setQty(4.0f);
			ses.save(prod1);
			//ses.update(prod1);  //tries to puts another object(1015) same product class in L1 cache, so exception will be raised(NonUniqueObjectException)
			tx.commit();
			System.out.println("Object is saved or updated");
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved or not updated");
			}
			he.printStackTrace();
		}
		finally {
			//close objects
			ses.close();
			factory.close();
		}
	}//main
}//class