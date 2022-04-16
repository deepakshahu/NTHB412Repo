package com.nit.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nit.entity.Doctor;
import com.nit.entity.Patient;
import com.nit.utility.HibernateUtil;

public class HospitalDAOImpl implements IHospitalDAO {

	@Override
	public void saveDataUsingParents() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//begin transaction
			tx = ses.beginTransaction();
			//Prepare parent objects
			Doctor doc1 = new Doctor();
			doc1.setDocName("Raja");
			doc1.setHospital("Apollo");
			Doctor doc2 = new Doctor();
			doc2.setDocName("Rajesh");
			doc2.setHospital("Care");
			//Prepare child objects
			Patient pat1 = new Patient();
			pat1.setPatName("Anil");
			pat1.setProblem("Heart");
			Patient pat2 = new Patient();
			pat2.setPatName("Ramesh");
			pat2.setProblem("Kidney");
			Patient pat3 = new Patient();
			pat3.setPatName("Suresh");
			pat3.setProblem("Corona");
			//Assign childs to parent
			doc1.setPatients(Set.of(pat1,pat2));
			doc2.setPatients(Set.of(pat1,pat2,pat3));
			//Assign parents to child
			pat1.setDoctors(Set.of(doc1));
			pat2.setDoctors(Set.of(doc1,doc2));
			pat3.setDoctors(Set.of(doc2));
			//save using parents
			ses.save(doc1);
			ses.save(doc2);
			tx.commit();
			System.out.println("Object are saved (Record is inserted)");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				System.out.println("Problem in saving objects");
			}
		}
	}//method

	@Override
	public void saveDataUsingChilds() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//begin transaction
			tx = ses.beginTransaction();
			//Prepare parent objects
			Doctor doc1 = new Doctor();
			doc1.setDocName("Raja1");
			doc1.setHospital("Apollo1");
			Doctor doc2 = new Doctor();
			doc2.setDocName("Rajesh1");
			doc2.setHospital("Care1");
			//Prepare child objects
			Patient pat1 = new Patient();
			pat1.setPatName("Anil1");
			pat1.setProblem("Heart1");
			Patient pat2 = new Patient();
			pat2.setPatName("Ramesh1");
			pat2.setProblem("Kidney1");
			Patient pat3 = new Patient();
			pat3.setPatName("Suresh1");
			pat3.setProblem("Corona1");
			//Assign childs to parent
			doc1.setPatients(Set.of(pat1,pat2));
			doc2.setPatients(Set.of(pat1,pat2,pat3));
			//Assign parents to child
			pat1.setDoctors(Set.of(doc1));
			pat2.setDoctors(Set.of(doc1,doc2));
			pat3.setDoctors(Set.of(doc2));
			//save using childs
			ses.save(pat1);
			ses.save(pat2);
			ses.save(pat3);
			tx.commit();
			System.out.println("Object are saved (Record is inserted)");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				System.out.println("Problem in saving objects");
			}
		}
	}//method

	@Override
	public void loadDataUsingParent() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			Query query = ses.createQuery("from Doctor");
			List<Doctor> list = query.getResultList();
			list.forEach(doc->{
				System.out.println("Parent :: "+doc);
				Set<Patient> childs = doc.getPatients();
				childs.forEach(pat->{
					System.out.println("Child :: "+pat);
				});
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//method

}//class
