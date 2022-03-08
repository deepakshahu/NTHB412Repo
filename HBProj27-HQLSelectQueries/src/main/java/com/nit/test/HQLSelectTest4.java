package com.nit.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class HQLSelectTest4 {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			/*//Executing HQL Entity select Query giving single record
			Query query = ses.createQuery("from Product where pid = :id");
			//Set param values
			query.setParameter("id", 27);
			//Execute the query
			Product prod = (Product) query.getSingleResult();
			if(prod==null)
				System.out.println("Record not found");
			else {
				System.out.println("Record found");
				System.out.println(prod);
			}*/
			
			/*System.out.println("=-------------------------------=");
			//Executing HQL Scalar select Query giving single record multiple specific column values
			Query query1 = ses.createQuery("select pid,pname from Product where pid=:id");
			query1.setParameter("id", 27);
			Object result = query1.getSingleResult();
			Object values[] = (Object[]) result;
			System.out.println(values[0]+" "+values[1]);*/
			
			/*System.out.println("---------------------------------------");
			//Executing HQL Scalar select Query giving single record single column values
			Query query2 = ses.createQuery("select pname from Product where pid=:id");
			query2.setParameter("id", 27);
			String name = (String) query2.getSingleResult();
			System.out.println("27 pid pname is :: "+name);*/
			
			/*System.out.println("----------------------------------");
			//Executing HQL select Query having single aggregate function
			Query query3 = ses.createQuery("select count(*) from Product");
			long count = (Long) query3.getSingleResult();
			System.out.println("Records count is :: "+count);*/
			
			/*System.out.println("----------------------------------");
			//Executing HQL select Query having multiple aggregate function
			Query query4 = ses.createQuery("select min(price), max(price), sum(price), avg(price) from Product");
			Object results[] = (Object[]) query4.getSingleResult();
			System.out.println("Min price :: "+results[0]);
			System.out.println("Max price :: "+results[1]);
			System.out.println("Sum price :: "+results[2]);
			System.out.println("Avg price :: "+results[3]);*/
			
			System.out.println("----------------------------------");
			Query query5 = ses.createQuery("from Product where price = (select max(price) from Product)");
			List<Product> list = query5.getResultList();
			list.forEach(prod->{
				System.out.println(prod);
			});
			
			System.out.println("----------------------------------");
			
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class