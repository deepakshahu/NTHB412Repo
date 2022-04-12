package com.nit.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory;
	
	static {
		System.out.println("HibernateUtil :: static block");
		//Create Configuration object
		Configuration cfg = new Configuration();
		//Load and read HB configuration file
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");
		//Build SessionFactory
		factory = cfg.buildSessionFactory();
	}
	
	//static factory method for SessionFactory object
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	public static Session getSession() {
		Session ses = null;
		if(factory!=null)
			ses = factory.openSession();
		return ses;
	}
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}
}