package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

import com.nit.entity.Product;

public class ObjectReloadTest {

	public static void main(String[] args) throws InterruptedException {

		//BootStrap / Activate the Hibernate
		Configuration cfg = new Configuration();

		//Specify the hibernate configuration file name and location
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");

		//Build SessionFactory object
		SessionFactory factory = cfg.buildSessionFactory();

		//Create Session object
		Session ses = factory.openSession();

		try(factory;ses){  //java9

			//load object
			Product prod = ses.load(Product.class, 1001);
			if(prod==null) {
				System.out.println("Record not found");
				return;
			}
			else {
				//Display record
				System.out.println(prod);
				System.out.println("App going into sleep state....");
				Thread.sleep(30000);  //sleep the app for 30ms
				//During this sleep time, modify the db table record
				ses.refresh(prod);  //reloads the object data from db table record
				System.out.println(prod);
			}//else
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
	}//main
}//class