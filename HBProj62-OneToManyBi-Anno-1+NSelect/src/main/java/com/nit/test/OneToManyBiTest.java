package com.nit.test;

import com.nit.dao.CompanyDAOImpl;
import com.nit.dao.ICompanyDAO;
import com.nit.utility.HibernateUtil;

public class OneToManyBiTest {

	public static void main(String[] args) {
		ICompanyDAO dao = new CompanyDAOImpl();
		//dao.loadDataUsingJoinsParentToChild();
		//dao.loadDataUsingJoinsChildToParent();
		
		//dao.loadDataUsingParentHQL();
		dao.loadDataUsingJPQBC();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
