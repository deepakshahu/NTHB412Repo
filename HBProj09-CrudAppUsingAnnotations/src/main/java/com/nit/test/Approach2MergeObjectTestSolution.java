package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Movie;
import com.nit.utility.HibernateUtil;

public class Approach2MergeObjectTestSolution {

	public static void main(String[] args) {
		//get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		//This example will work with normal try catch block and not with TWR
		try{
			//Begin Transaction
			tx = ses.beginTransaction();
			//Load object operation
			Movie movie = ses.get(Movie.class, 1003);  //Loads movie class object(1015) and puts in L1 cache 
			System.out.println(movie);
			//Update Object operation
			Movie movie1 = new Movie();
			movie1.setMid(1003);
			movie1.setMname("Antim: The Final Truth");
			movie1.setHeroName("Salman Khan");
			movie1.setBudget(1307.0f);
			System.out.println(movie.hashCode()+" "+movie1.hashCode());
			Movie movie2 = (Movie) ses.merge(movie1);
			System.out.println(movie.hashCode()+" "+movie1.hashCode()+" "+movie2.hashCode());
			tx.commit();
			System.out.println("Object is merged");
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not merged");
			}
			he.printStackTrace();
		}
		finally {
			//close objects
			ses.close();
			factory.close();
		}
	}//main
}//class