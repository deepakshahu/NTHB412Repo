package com.nit.test;

import com.nit.dao.IOneToOneFKDAO;
import com.nit.dao.OneToOneFKDAOImpl;
import com.nit.utility.HibernateUtil;

public class OneToOneFKTest {

	public static void main(String[] args) {
		//create DAO class object
		IOneToOneFKDAO dao = new OneToOneFKDAOImpl();
		//dao.saveDataUsingChild();
		dao.loadDataUsingChild();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main

}//class
