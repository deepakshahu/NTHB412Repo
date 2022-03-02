package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil_MySQL;
import com.nit.utility.HibernateUtil_Oracle;

public class TransferProductTest {

	public static void main(String[] args) {

		//Get SessionFactory object
		SessionFactory oraclefactory = HibernateUtil_Oracle.getSessionFactory();
		SessionFactory mysqlfactory = HibernateUtil_MySQL.getSessionFactory();
		//Get Session object
		Session oracleSes = HibernateUtil_Oracle.getSession();
		Session mysqlSes = HibernateUtil_MySQL.getSession();
		Transaction mysqlTx = null;
		try(oraclefactory;mysqlfactory;oracleSes;mysqlSes){
			//Get object/record from oracle db table
			Product prod = oracleSes.get(Product.class, 27);
			//Save the object/record into mysql db table
			mysqlTx = mysqlSes.beginTransaction();
			mysqlSes.save(prod);
			mysqlTx.commit();
			System.out.println("Record/Object is transferred from oracle to mysql db table");
		}
		catch(HibernateException he) {
			if(mysqlTx!=null && mysqlTx.getStatus()!=null && mysqlTx.getRollbackOnly()) {
				mysqlTx.rollback();
				System.out.println("Problem in transferring record");
			}
			he.printStackTrace();
		}

	}//main
}//class