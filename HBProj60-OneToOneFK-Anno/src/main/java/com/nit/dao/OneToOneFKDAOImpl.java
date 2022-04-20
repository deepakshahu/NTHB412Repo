package com.nit.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nit.entity.Passport;
import com.nit.entity.Person;
import com.nit.utility.HibernateUtil;

public class OneToOneFKDAOImpl implements IOneToOneFKDAO {

	@Override
	public void saveDataUsingChild() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//begin transaction
			tx = ses.beginTransaction();
			Person per = new Person();
			per.setPname("Deepak");
			per.setPaddrs("Mumbai");
			Passport pspt = new Passport();
			pspt.setCountry("India");
			pspt.setExpiryDate(LocalDate.of(2030, 10, 25));
			//set parent to child
			pspt.setPersonDetails(per);
			//save object
			ses.save(pspt);
			tx.commit();
			System.out.println("Objects are saved");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Objects are not saved");
			}
		}//catch
	}//method

	@Override
	public void loadDataUsingChild() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			Query query = ses.createQuery("from Passport");
			List<Passport> list = query.getResultList();
			list.forEach(pspt->{
				System.out.println("Child :: "+pspt);
				Person per = pspt.getPersonDetails();
				System.out.println("Parent :: "+per);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
	}//method
	
}//class