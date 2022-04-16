package com.nit.test;

import com.nit.dao.HospitalDAOImpl;
import com.nit.dao.IHospitalDAO;
import com.nit.utility.HibernateUtil;

public class ManyToManyTest {

	public static void main(String[] args) {
		//Create DAO class object
		IHospitalDAO dao = new HospitalDAOImpl();
		//dao.saveDataUsingParents();
		//dao.saveDataUsingChilds();
		dao.loadDataUsingParent();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main

}//class
