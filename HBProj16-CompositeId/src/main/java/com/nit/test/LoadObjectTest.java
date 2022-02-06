package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.PrgmrProjId;
import com.nit.entity.ProgrammerProjectInfo;
import com.nit.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//Prepare id class object
			PrgmrProjId id = new PrgmrProjId(101, 1001);
			//Load object
			ProgrammerProjectInfo info = ses.get(ProgrammerProjectInfo.class, id);
			if(info!=null)
				System.out.println(info);
			else
				System.out.println("Record not found");
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
	}//main
}//class