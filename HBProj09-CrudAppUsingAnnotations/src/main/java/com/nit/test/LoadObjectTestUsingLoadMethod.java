package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class LoadObjectTestUsingLoadMethod {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//Load object
			Movie movie = ses.load(Movie.class, 1001);
			System.out.println("Movie is :: "+movie.getMid()+" "+movie.getMname()+" "+movie.getHeroName()+" "+movie.getBudget());
		}
		catch(HibernateException he) {
			he.printStackTrace();
			System.out.println("Movie not found");
		}
	}//main
}//class