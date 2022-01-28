package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class Approach1MergeObjectTest {

	public static void main(String[] args) {
		//get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){  //java9
			//Begin Transaction
			tx = ses.beginTransaction();
			Product prod = new Product();
			prod.setPid(1016);
			prod.setPname("Bottle");
			prod.setPrice(776.23f);
			prod.setQty(10.0f);
			Product prod1 = (Product) ses.merge(prod);
			tx.commit();
			System.out.println("Given object data : "+prod+" hashCode : "+prod.hashCode());
			System.out.println("Recieved object data : "+prod1+" hashCode : "+prod1.hashCode());
			System.out.println("Object is saved or updated");
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved or not updated");
			}
			he.printStackTrace();
		}
	}//main
}//class