package com.nit.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class HQLSelectTest1 {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try{
			//Prepare Query object having HQL/JPQL query
			Query query = ses.createQuery("from Product");
			List<Product> list = query.list();  //executes the HQL query

			/*for(Product p:list) {               //Enhanced for loop
				System.out.println(p);
			}*/
			/*list.forEach(prod->{              //Java8 forEach(-) method
				System.out.println(prod);
			});*/
			list.forEach(System.out::println);  //Java8 forEach(-) method + method reference

			//Executing HQL/JPQL query lazily using query.iterate() method

			/*Query query = ses.createQuery("from Product");
		Iterator<Product> it = query.iterate();
		while(it.hasNext()) {
			Product prod = it.next();
			System.out.println(prod);
		}*/
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class