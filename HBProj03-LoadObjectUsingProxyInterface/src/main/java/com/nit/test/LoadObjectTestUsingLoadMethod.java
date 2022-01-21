package com.nit.test;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nit.entity.IProduct;
import com.nit.entity.Product;

public class LoadObjectTestUsingLoadMethod {

	public static void main(String[] args) {

		//BootStrap / Activate the Hibernate
		Configuration cfg = new Configuration();

		//Specify the hibernate configuration file name and location
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");

		//Build SessionFactory object
		SessionFactory factory = cfg.buildSessionFactory();

		//Create Session object
		Session ses = factory.openSession();

		try(factory;ses){  //java9
			//Load object
			IProduct prod = ses.load(Product.class, 1013);
			System.out.println(prod.getClass()+" "+prod.getClass().getSuperclass()+" "+Arrays.toString(prod.getClass().getInterfaces()));
			System.out.println("---------------------------");
			System.out.println(prod.getPid()+" "+prod.getPname()+" "+prod.getPrice()+" "+prod.getQty());
		}
		catch(HibernateException he) {
			System.out.println("Record not found");
			he.printStackTrace();
		}
		
	}//main
}//class