package com.nit.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.PersonDetails;
import com.nit.utility.HibernateUtil;

public class CollectionMappingInsertTest {

	public static void main(String[] args) {
		//Create SessionFactory, Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//begin tx
			tx = ses.beginTransaction();
			//prepare object
			PersonDetails details = new PersonDetails();
			details.setPname("Deepak");
			details.setPaddrs("Mumbai");
			details.setNickNames(List.of("Shahu", "Chipak"));
			details.setFriends(List.of("Harsh","Mintu","Ankit"));
			details.setContactNumbers(Set.of(999999999L,888888888L));
			details.setIdDetails(Map.of("aadhar",869247588457L,"voterId",45879623L));
			
			//save object
			int idVal = (int) ses.save(details);
			System.out.println("Generated id value is :: "+idVal);
			//commit the tx
			tx.commit();
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in inserting the record");
			}
		}
		
	}//main
}//class