package com.nit.test;

import com.nit.dao.CompanyDAOImpl;
import com.nit.dao.ICompanyDAO;
import com.nit.utility.HibernateUtil;

public class WorkingWithLOBsTest {

	public static void main(String[] args) {
		ICompanyDAO dao = new CompanyDAOImpl();
		//dao.saveData();
		dao.loadData();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
