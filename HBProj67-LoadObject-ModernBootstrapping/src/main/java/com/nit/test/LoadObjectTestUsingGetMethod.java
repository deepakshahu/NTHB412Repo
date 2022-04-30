package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class LoadObjectTestUsingGetMethod {

	public static void main(String[] args) {
		//get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){  //java9 TWR
			//Load object
			Product prod = ses.get(Product.class, 1003);
			if(prod==null)
				System.out.println("Product not found");
			else
				System.out.println(prod);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class