package com.nit.test;

import com.nit.dao.IOneToOnePKDAO;
import com.nit.dao.OneToOnePKDAOImpl;
import com.nit.utility.HibernateUtil;

public class OneToOnePKTest {

	public static void main(String[] args) {
		//create DAO class object
		IOneToOnePKDAO dao = new OneToOnePKDAOImpl();
		//dao.saveDataUsingParent();
		dao.saveDataUsingChild();
		//dao.loadDataUsingParent();
		//dao.loadDataUsingChild();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main

}//class
