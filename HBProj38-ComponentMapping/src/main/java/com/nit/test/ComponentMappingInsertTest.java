package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.JobDetails;
import com.nit.entity.Person;
import com.nit.utility.HibernateUtil;

public class ComponentMappingInsertTest {

	public static void main(String[] args) {
		
		//Create SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Create Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses) {
			//begin tx
			tx = ses.beginTransaction();
			//prepare objects
			JobDetails details = new JobDetails();
			details.setDesg("MANAGER");
			details.setCompany("TCS");
			details.setSalary(190000.0);
			
			Person per = new Person();
			per.setPname("Haresh");
			per.setPaddrs("Mumbai");
			per.setDetails(details);
			
			//save object
			int idVal = (int) ses.save(per);
			System.out.println("Person is saved with id value :: "+idVal);
			//commit the tx
			tx.commit();
		}//try
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in saving data");
			}
			he.printStackTrace();
		}//catch
	}//main
}//class