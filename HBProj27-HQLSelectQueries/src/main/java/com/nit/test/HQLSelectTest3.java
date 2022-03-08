package com.nit.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.nit.utility.HibernateUtil;

public class HQLSelectTest3 {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			/*//executing HQL scalar select query (retrieving specific multiple column values)
			Query query = ses.createQuery("select pid,pname,price from Product where price>=:min and price<=:max");
			//Set param values
			query.setParameter("min", 1000.0f);
			query.setParameter("max", 30000.0f);
			List<Object[]> list = query.list();
			list.forEach(row->{
				for(Object val:row) {
					System.out.print(val+" ");
				}
				System.out.println();
			});*/
			
			System.out.println("---------------------------------------");
			//executing HQL scalar select query (retrieving specific single column value)
			Query query1 = ses.createQuery("select pname from Product where qty>=:min and qty<=:max");
			//Set param values
			query1.setParameter("min", 2.0f);
			query1.setParameter("max", 15.0f);
			List<Object> list1 = query1.list();
			list1.forEach(pname->{
				System.out.println(pname);
			});
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class