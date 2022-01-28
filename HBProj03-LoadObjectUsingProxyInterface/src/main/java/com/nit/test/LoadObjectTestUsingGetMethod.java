package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nit.entity.Product;

public class LoadObjectTestUsingGetMethod {

	public static void main(String[] args) {

		//BootStrap / Activate the Hibernate
		Configuration cfg = new Configuration();

		//Specify the hibernate configuration file name and location
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");

		//Build SessionFactory object
		SessionFactory factory = cfg.buildSessionFactory();

		//Create Session object
		Session ses = factory.openSession();

		try(factory;ses){  //java9 TWR
			//Load object
			Product prod = ses.get(Product.class, 1013);
			System.out.println(prod.getClass());
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