package com.nit.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Customer;
import com.nit.entity.Employee;
import com.nit.entity.Person;
import com.nit.utility.HibernateUtil;

public class TPCC_InheritanceMappingSelectTest {

	public static void main(String[] args) {
		//Get SessionFactory, Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//execute HQL query
			Query query1 = ses.createQuery("from Employee");
			List<Employee> list1 = query1.getResultList();
			list1.forEach(emp->{
				System.out.println(emp);
			});
			System.out.println("===============================");
			Query query2 = ses.createQuery("from Customer");
			List<Customer> list2 = query2.getResultList();
			list2.forEach(cust->{
				System.out.println(cust);
			});
			System.out.println("===============================");
			Query query3 = ses.createQuery("from Person");
			List<Person> list3 = query3.getResultList();
			list3.forEach(per->{
				System.out.println(per);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}

}
