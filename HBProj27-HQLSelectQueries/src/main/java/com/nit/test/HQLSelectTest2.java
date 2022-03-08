package com.nit.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class HQLSelectTest2 {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try{
			/*//Prepare Query object having HQL/JPQL query
			Query query = ses.createQuery("from Product where pid>=?1 and pid<=?2");
			//set query param values
			query.setParameter(1, 10);
			query.setParameter(2, 1050);
			//Execute the HQL query
			List<Product> list = query.list();  //executes the HQL query
			//Process the Result
			list.forEach(prod->{
				System.out.println(prod);
			});*/
			
			//Prepare Query object having HQL/JPQL query with Named parameters
			Query query = ses.createQuery("from Product where pname in (?1,:prod2, :prod3) order by pname asc");
			//Set values to named params
			query.setParameter(1, "Sofa");
			query.setParameter("prod2", "Chair");
			query.setParameter("prod3", "Table");
			//Execute the query
			List<Product> list = query.list();
			//Process the result
			list.forEach(prod->{
				System.out.println(prod);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class