package com.nit.test;

import com.nit.dao.IPersonDetailsDAO;
import com.nit.dao.PersonDetailsDAOImpl;
import com.nit.utility.HibernateUtil;

public class OneToManyUniTest {

	public static void main(String[] args) {
		IPersonDetailsDAO dao = new PersonDetailsDAOImpl();
		//dao.saveDataUsingParent();
		//dao.loadDataUsingParent();
		//dao.addChildToExistingParent();
		//dao.deleteAllChildsOfAParent();
		//dao.deleteOneChildFromCollectionOfChildsOfAParent();
		dao.deleteParentAndItsChilds();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}

}
