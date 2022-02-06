package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.PrgmrProjId;
import com.nit.entity.ProgrammerProjectInfo;
import com.nit.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//begin transaction
			tx = ses.beginTransaction();
			//Prepare id class object
			PrgmrProjId id = new PrgmrProjId(100,1000);
			//Prepare Entity class object
			ProgrammerProjectInfo info = new ProgrammerProjectInfo(id, "Suresh", "openFx", 90000, 3000000);
			//Save object
			PrgmrProjId idVal = (PrgmrProjId) ses.save(info);
			tx.commit();
			System.out.println("Object/record is saved with the id value :: "+idVal);
		}
		catch(HibernateException he) {
			if(tx!=null || tx.getStatus()!=null  || tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved");
			}//if
			he.printStackTrace();
		}//catch
	}//main
}//class