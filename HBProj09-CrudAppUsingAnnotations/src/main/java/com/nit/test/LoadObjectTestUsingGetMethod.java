package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class LoadObjectTestUsingGetMethod {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//Load object
			Movie movie = ses.get(Movie.class, 1004);
			if(movie==null)
				System.out.println("Movie not found");
			else
				System.out.println(movie);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class