package com.nit.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nit.entity.LibraryMembership;
import com.nit.entity.Student;
import com.nit.utility.HibernateUtil;

public class OneToOnePKDAOImpl implements IOneToOnePKDAO {

	@Override
	public void saveDataUsingParent() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//begin transaction
			tx = ses.beginTransaction();
			Student stud = new Student();
			stud.setSname("Deepak");
			stud.setSadd("Mumbai");
			LibraryMembership lib = new LibraryMembership();
			lib.setType("gold");
			lib.setDoj(LocalDate.now());
			//set parent to child
			lib.setStudentDetails(stud);
			stud.setLibraryDetails(lib);
			//save objs
			ses.save(stud);
			tx.commit();
			System.out.println("Objects are saved");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Objects are not saved");
			}
		}//catch
	}//method

	@Override
	public void saveDataUsingChild() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//begin transaction
			tx = ses.beginTransaction();
			Student stud = new Student();
			stud.setSname("Suresh");
			stud.setSadd("Hyd");
			LibraryMembership lib = new LibraryMembership();
			lib.setType("Silver");
			lib.setDoj(LocalDate.now());
			//set parent to child
			lib.setStudentDetails(stud);
			stud.setLibraryDetails(lib);
			//save objs
			ses.save(lib);
			tx.commit();
			System.out.println("Objects are saved");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Objects are not saved");
			}
		}//catch
	}//method

	@Override
	public void loadDataUsingParent() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			Query query = ses.createQuery("from Student");
			List<Student> list = query.getResultList();
			list.forEach(stud->{
				System.out.println("Parent :: "+stud);
				LibraryMembership lib = stud.getLibraryDetails();
				System.out.println("Child :: "+lib);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
	}//method

	@Override
	public void loadDataUsingChild() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			Query query = ses.createQuery("from LibraryMembership");
			List<LibraryMembership> list = query.getResultList();
			list.forEach(lib->{
				System.out.println("Parent :: "+lib);
				Student stud = lib.getStudentDetails();
				System.out.println("Child :: "+stud);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}//catch
	}//method

}//class
