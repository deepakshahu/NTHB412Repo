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

public class JPAQBC_SelectOperationTest {

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
			CriteriaQuery<Product> ctQuery = ctBuilder.createQuery(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logic
			List<Product> list = query.getResultList();
			//Process the Result
			list.forEach(System.out::println);
			 */

			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Product> ctQuery = ctBuilder.createQuery(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//Specify root object for select operation
			ctQuery.select(root).where(ctBuilder.and(ctBuilder.ge(root.get("price"), 5000.0f), ctBuilder.le(root.get("price"), 20000.0f)));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logics
			List<Product> list = query.getResultList();
			//Process the Result
			list.forEach(System.out::println);
			 */

			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Product> ctQuery = ctBuilder.createQuery(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//Apply where clause condition
			//Select * from product where pname IN ("Table","Bed","Chair") order by price asc;
			ctQuery.select(root).where(root.get("pname").in("Table","Bed","Chair")).orderBy(ctBuilder.asc(root.get("price")));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logics
			List<Product> list = query.getResultList();
			//Process the Result
			list.forEach(System.out::println);
			 */

			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Product> ctQuery = ctBuilder.createQuery(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//Apply where clause condition and like operator
			ctQuery.select(root).where(ctBuilder.like(root.get("pname"), "S%"));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logics
			List<Product> list = query.getResultList();
			//Process the Result
			list.forEach(System.out::println);
			 */

			/*
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Product> ctQuery = ctBuilder.createQuery(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//Specify root object for select operation and qty range
			ctQuery.select(root).where(ctBuilder.and(ctBuilder.ge(root.get("qty"), 2.0f), ctBuilder.le(root.get("qty"), 10.0f)));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logics
			List<Product> list = query.getResultList();
			//Process the Result
			list.forEach(System.out::println);
			 */
			
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Object> ctQuery = ctBuilder.createQuery(Object.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctQuery.from(Product.class);  //SELECT * FROM PRODUCT
			//Specify root object for select operation having highest price
			ctQuery.select(ctBuilder.max(root.get("price")));
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logics
			Object maxSalary = query.getSingleResult();
			//Process the Result
			System.out.println("Maximum salary :: "+maxSalary);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}// main
}// class