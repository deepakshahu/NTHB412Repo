package com.nit.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.Customer;
import com.nit.entity.Employee;
import com.nit.entity.Person;
import com.nit.utility.HibernateUtil;

public class TPSC_InheritanceMappingInsertTest {

	public static void main(String[] args) {
		//Get SessionFactory, Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//begin transaction
			tx = ses.beginTransaction();
			//prepare objects
			Person per = new Person();
			per.setPname("Deepak");
			per.setCompany("TCS");

			Employee emp = new Employee();
			emp.setPname("Harsh");
			emp.setCompany("Wipro");
			emp.setDesg("Developer");
			emp.setDeptno(1001);
			emp.setSalary(56000.0);

			Customer cust = new Customer();
			cust.setPname("Ankit");
			cust.setCompany("SAMSUNG");
			cust.setBillNo(2345);
			cust.setBillAmt(9000.0);

			//save objects
			ses.save(per);
			ses.save(emp);
			ses.save(cust);

			tx.commit();
			System.out.println("Objects are saved");
		}
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				System.out.println("Problem in saving objects");
				tx.rollback();
			}
			he.printStackTrace();
		}
	}

}
