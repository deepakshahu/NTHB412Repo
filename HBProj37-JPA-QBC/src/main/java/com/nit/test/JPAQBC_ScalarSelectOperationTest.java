package com.nit.test;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class JPAQBC_ScalarSelectOperationTest {

	public static void main(String[] args) {

		// Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try (factory; ses) {
			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Object[]> ctQuery = ctBuilder.createQuery(Object[].class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//specify specific multiple column and where clause condition
			//select pid,pname from product where price>=1000.0 and price<=20000.0 order by pname asc
			ctQuery.multiselect(root.get("pid"), root.get("pname")).where(ctBuilder.and(ctBuilder.ge(root.get("price"), 1000.0f), 
											ctBuilder.le(root.get("price"), 20000.0f))).orderBy(ctBuilder.asc(root.get("pname")));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logic
			List<Object[]> list = query.getResultList();
			//Process the Result
			list.forEach(row->{
				for(Object val:row) {
					System.out.print(val+" ");
				}
				System.out.println();
			});
			 */

			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<String> ctQuery = ctBuilder.createQuery(String.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//specify specific single column and where clause condition
			//select pname from product where pname like 'S%'
			ctQuery.multiselect(root.get("pname")).where(ctBuilder.like(root.get("pname"), "S%"));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logic
			List<String> list = query.getResultList();
			//Process the Result
			list.forEach(System.out::println);
			 */

			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Long> ctQuery = ctBuilder.createQuery(Long.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//specify specific multiple column and where clause condition
			//select count(pid) from product
			ctQuery.multiselect(ctBuilder.count(root.get("pid")));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logic
			long count = (long) query.getSingleResult();
			//Process the Result
			System.out.println("Count of records :: "+count);
			 */

			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Object[]> ctQuery = ctBuilder.createQuery(Object[].class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//specify specific multiple column and where clause condition
			//select count(pid), sum(price), avg(qty), max(price), min(price) from product
			ctQuery.multiselect(ctBuilder.count(root.get("pid")), 
								ctBuilder.sum(root.get("price")), 
								ctBuilder.avg(root.get("qty")),
								ctBuilder.max(root.get("price")),
								ctBuilder.min(root.get("price")));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logic
			Object result[] = (Object[]) query.getSingleResult();
			//Process the Result
			System.out.println("Count of records :: "+result[0]);
			System.out.println("Sum of prices :: "+result[1]);
			System.out.println("Avg of qty :: "+result[2]);
			System.out.println("Max of prices :: "+result[3]);
			System.out.println("Min of prices :: "+result[4]);
			 */

			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Product> ctQuery = ctBuilder.createQuery(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//pagination settings
			query.setFirstResult(0);
			query.setMaxResults(3);
			//Process the result
			List<Product> list = query.getResultList();
			list.forEach(prod->{
				System.out.println(prod);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}// main
}// class