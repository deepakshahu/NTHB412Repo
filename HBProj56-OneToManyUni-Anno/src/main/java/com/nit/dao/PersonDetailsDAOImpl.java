package com.nit.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nit.entity.PersonDetails;
import com.nit.entity.PhoneNumber;
import com.nit.utility.HibernateUtil;

public class PersonDetailsDAOImpl implements IPersonDetailsDAO {

	@Override
	public void saveDataUsingParent() {
		//get access to Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			tx = ses.beginTransaction();
			//prepare parent object
			PersonDetails details = new PersonDetails();
			details.setPname("Suraj");
			details.setPaddrs("Pune");

			//prepare child object
			PhoneNumber ph1 = new PhoneNumber();
			ph1.setMobileNo(77777777777L);
			ph1.setNumberType("School");
			ph1.setProvider("Idea");

			PhoneNumber ph2 = new PhoneNumber();
			ph2.setMobileNo(666666666666L);
			ph2.setNumberType("Tution");
			ph2.setProvider("Vi");

			//set child objects to parent object
			details.setPhones(Set.of(ph1,ph2));

			//save parent object
			int idVal = (Integer) ses.save(details);
			System.out.println("Generated id value :: "+idVal);

			//commit the tx
			tx.commit();
			System.out.println("Parent and the associated childs are saved");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Parent and the associated childs are not saved");
			}
		}

	}//method

	@Override
	public void loadDataUsingParent() {
		//get access to Session object
		Session ses = HibernateUtil.getSession();
		try(ses){
			Query query = ses.createQuery("from PersonDetails");
			List<PersonDetails> list = query.getResultList();
			list.forEach(pd->{
				System.out.println("Parent :: "+pd);
				Set<PhoneNumber> childs = pd.getPhones();
				System.out.println("Child count :: "+childs.size());
				/*childs.forEach(ph->{
					System.out.println("Child :: "+ph);
				});*/
				System.out.println("=========================================");
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//method

	@Override
	public void addChildToExistingParent() {
		//get access to Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			tx = ses.beginTransaction();
			//load existing parent
			PersonDetails pd = ses.get(PersonDetails.class, 1);
			//get all childs of parent
			Set<PhoneNumber> childs = pd.getPhones();
			//create new child objs
			PhoneNumber ph = new PhoneNumber();
			ph.setMobileNo(8745896521L);
			ph.setNumberType("Office");
			ph.setProvider("VI");
			//add new child to existing parent
			childs.add(ph);
			tx.commit();
			System.out.println("Child is added to existing childs of a parent");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Child is not added to existing childs of a parent");
			}
		}
	}//method

	@Override
	public void deleteAllChildsOfAParent() {
		//get access to Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			tx = ses.beginTransaction();
			//load a parent
			PersonDetails details = ses.get(PersonDetails.class, 1);
			//get all childs of a parent
			Set<PhoneNumber> childs = details.getPhones();
			childs.removeAll(childs);
			tx.commit();
			System.out.println("All childs of a parent are removed");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in removing childs of a parent");
			}
		}
	}//method

	@Override
	public void deleteOneChildFromCollectionOfChildsOfAParent() {
		//get access to Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			tx = ses.beginTransaction();
			//load a parent
			PersonDetails details = ses.get(PersonDetails.class, 1);
			//get all childs of a parent
			Set<PhoneNumber> childs = details.getPhones();
			//Load specific child that u want to delete
			PhoneNumber ph = ses.get(PhoneNumber.class, 1004);
			childs.remove(ph);
			tx.commit();
			System.out.println("Specific child of parent is deleted");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in removing specific child of a parent");
			}
		}
	}//method

	@Override
	public void deleteParentAndItsChilds() {
		//get access to Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			tx = ses.beginTransaction();
			//load a parent which leads to loading the childs
			PersonDetails details = ses.get(PersonDetails.class, 2);
			//delete parent which leads to deleting the childs
			ses.delete(details);
			tx.commit();
			System.out.println("Parent and its associated childs are deleted");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Parent and its associated childs are not deleted");
			}
		}
	}//method

}//class