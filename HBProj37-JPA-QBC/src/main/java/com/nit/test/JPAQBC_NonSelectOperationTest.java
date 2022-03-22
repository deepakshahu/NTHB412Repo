package com.nit.test;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class JPAQBC_NonSelectOperationTest {

	public static void main(String[] args) {

		// Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try (factory; ses) {
			/*
			//begin transaction
			tx = ses.beginTransaction();
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaUpdate object
			CriteriaUpdate<Product> ctUpdate = ctBuilder.createCriteriaUpdate(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctUpdate.from(Product.class);  //SELECT * FROM PRODUCT
			//Specify values to set and the conditions to apply
			ctUpdate.set("price", 2000.0f).where(ctBuilder.le(root.get("qty"), 10));
			//Create Query object having CriteriaUpdate object
			Query query = ses.createQuery(ctUpdate);
			//Process the result
			int count = query.executeUpdate();
			System.out.println("No of records that are affected :: "+count);
			//commit the tx
			tx.commit();
			*/
			
			//begin transaction
			tx = ses.beginTransaction();
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaDelete object
			CriteriaDelete<Product> ctDelete = ctBuilder.createCriteriaDelete(Product.class);
			//Create Root object specifying the from operation
			Root<Product> root = ctDelete.from(Product.class);  //SELECT * FROM PRODUCT
			//Specify values to set and the conditions to apply
			ctDelete.where(ctBuilder.ge(root.get("price"), 2025));
			//Create Query object having CriteriaDelete object
			Query query = ses.createQuery(ctDelete);
			//Process the result
			int count = query.executeUpdate();
			System.out.println("No of records that are affected :: "+count);
			//commit the tx
			tx.commit();			
		}
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				System.out.println("Problem in record updation");
				tx.rollback();
			}
			he.printStackTrace();
		}
	}// main
}// class