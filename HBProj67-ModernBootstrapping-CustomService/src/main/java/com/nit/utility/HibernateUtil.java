package com.nit.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.boot.registry.selector.spi.StrategySelector;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.nit.service.CustomJDBCConnectionService;

public class HibernateUtil {
	private static SessionFactory factory;
	
	/*static {
		System.out.println("HibernateUtil :: static block");
		//Create Configuration object
		Configuration cfg = new Configuration();
		//Load and read HB configuration file
		cfg.configure("com/nit/cfgs/hibernate.cfg.xml");
		cfg.addResource("com/nit/entity/Product.hbm.xml");
		//Create ServiceRegistryBuilder
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		//Create ServiceRegistry
		StandardServiceRegistry registry = builder.applySettings(cfg.getProperties()).build();
		//Build SessionFactory
		factory = cfg.buildSessionFactory(registry);
	}*/
	
	static {
		System.out.println("HibernateUtil :: static block");
		//Create Configuration object
		Configuration cfg = new Configuration();
		//Create ServiceRegistryBuilder
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.addService(ConnectionProvider.class, new CustomJDBCConnectionService());
		//Create ServiceRegistry
		StandardServiceRegistry registry = builder.configure("com/nit/cfgs/hibernate.cfg.xml").build();
		System.out.println(registry.getParentServiceRegistry());
		System.out.println(registry.getService(ClassLoaderService.class)+" "+registry.getService(ConnectionProvider.class)+" "+registry.getService(StrategySelector.class));
		//Build SessionFactory
		factory = cfg.buildSessionFactory(registry);
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
}