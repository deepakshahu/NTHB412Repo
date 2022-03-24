package com.nit.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.JobDetails;
import com.nit.entity.Person;
import com.nit.utility.HibernateUtil;

public class ComponentMappingSelectTest {

	public static void main(String[] args) {
		
		//Create SessionFactory, Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		try(factory;ses) {
			
			//Create HQL/JPQL query
			Query query1 = ses.createQuery("from Person");
			//execute the query
			List<Person> list = query1.getResultList();
			//process the result
			list.forEach(per->{
				System.out.println("Person details :: "+per.getPid()+" "+per.getPname()+" "+per.getPaddrs());
				JobDetails details = per.getDetails();
				System.out.println("JobDetails :: "+details.getDesg()+" "+details.getCompany()+" "+details.getSalary());
			});
			
			/*
			//Create HQL/JPQL query
			Query query1 = ses.createQuery("from Person where details.company=?1");
			//Set param values
			query1.setParameter(1, "HCL");
			//execute the query
			List<Person> list = query1.getResultList();
			//process the result
			list.forEach(per->{
				System.out.println("Person details :: "+per.getPid()+" "+per.getPname()+" "+per.getPaddrs());
				JobDetails details = per.getDetails();
				System.out.println("JobDetails :: "+details.getDesg()+" "+details.getCompany()+" "+details.getSalary());
			});
			*/
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
	}//main
}//class