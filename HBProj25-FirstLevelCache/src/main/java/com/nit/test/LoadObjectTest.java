package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//Load Object
			Movie movie = ses.get(Movie.class, 711);  //Collects from DB and keeps in L1 cache
			System.out.println(movie);
			System.out.println("==============================");
			Movie movie1 = ses.get(Movie.class, 711);  //Collects from L1 cache
			System.out.println(movie1);
			System.out.println("==============================");
			Movie movie2 = ses.get(Movie.class, 1);  //Collects from DB and keeps in L1 cache
			System.out.println(movie2);
			System.out.println("==============================");
			ses.evict(movie);  //takes 711 id value & removes the movie object from L1 cache, any reference you can take
			Movie movie3 = ses.get(Movie.class, 711);  //Collects from DB and keeps in L1 cache
			System.out.println(movie3);
			System.out.println("==============================");
			//To empty the L1 cache
			ses.clear();  //Removes all the objects from L1 cache
			Movie movie4 = ses.get(Movie.class, 711);  //Collects from DB and keeps in L1 cache
			System.out.println(movie4);
			
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class