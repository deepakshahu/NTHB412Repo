package com.nit.test;

import com.nit.dao.CompanyDAOImpl;
import com.nit.dao.ICompanyDAO;
import com.nit.utility.HibernateUtil;

public class OneToManyBiTest {

	public static void main(String[] args) {
		ICompanyDAO dao = new CompanyDAOImpl();
		//dao.saveDataUsingParent();
		//dao.saveDataUsingChild();
		//dao.loadDataUsingParent();
		dao.loadDataUsingChild();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
