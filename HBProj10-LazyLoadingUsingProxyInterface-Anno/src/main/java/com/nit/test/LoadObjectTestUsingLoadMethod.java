package com.nit.test;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.proxy.IMovie;
import com.nit.utility.HibernateUtil;

public class LoadObjectTestUsingLoadMethod {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//Load object
			IMovie movie = ses.load(IMovie.class, 1001);
			System.out.println(movie.getClass()+" <---> "+movie.getClass().getSuperclass()+" <--> "+Arrays.toString(movie.getClass().getInterfaces()));
			//System.out.println("Movie is :: "+movie.getMid()+" "+movie.getMname()+" "+movie.getHeroName()+" "+movie.getBudget());
			//System.out.println(movie);
		}
		catch(HibernateException he) {
			he.printStackTrace();
			System.out.println("Movie not found");
		}
	}//main
}//class