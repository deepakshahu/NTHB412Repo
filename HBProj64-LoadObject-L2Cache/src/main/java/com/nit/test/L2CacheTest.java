package com.nit.test;

import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class L2CacheTest {

	public static void main(String[] args) {
		//get SessionFactory and Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//Load object
			Product prod = ses.get(Product.class, 1003);  //gets from DB and keeps in L2,L1 cache
			System.out.println(prod);
			System.out.println("------------------------------");
			Product prod1 = ses.get(Product.class, 1003);  //gets from L1 cache
			System.out.println(prod1);
			System.out.println("------------------------------");
			ses.evict(prod1);  //removes prod1 from L1 cache
			Product prod2 = ses.get(Product.class, 1003);  //gets from L2 cache and keeps in L1 cache
			System.out.println(prod2);
			System.out.println("------------------------------");
			ses.clear(); //removes all from L1 cache
			Cache cache = factory.getCache();
			cache.evictAll();  //emptying L2 cache
			Product prod3 = ses.get(Product.class, 1003);  //gets from DB and keeps in L2,L1 caches
			System.out.println(prod3);
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
	}//main
}//class