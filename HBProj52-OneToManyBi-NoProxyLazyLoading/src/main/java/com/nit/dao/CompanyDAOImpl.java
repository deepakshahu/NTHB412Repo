package com.nit.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nit.entity.Department;
import com.nit.entity.Employee;
import com.nit.entity.IDepartment;
import com.nit.utility.HibernateUtil;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void saveDataUsingParent() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//begin transaction
			tx = ses.beginTransaction();
			//prepare parent objects
			Department dept = new Department();
			dept.setDeptName("IT");
			dept.setDeptHead("JAMES");
			//prepare child objects
			Employee emp1 = new Employee();
			emp1.setEmpName("Deepak");
			emp1.setEmpSalary(95000.0f);
			Employee emp2 = new Employee();
			emp2.setEmpName("Harsh");
			emp2.setEmpSalary(56000.0f);
			//set childs to parent (for one to many)
			dept.setEmployees(Set.of(emp1,emp2));
			//set parent to childs (for many to one)
			emp1.setDept(dept);
			emp2.setDept(dept);
			//save through parent
			ses.save(dept);
			tx.commit();
			System.out.println("Parent to childs are saved");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in saving objects (parent to childs)");
			}
		}
	}//method

	@Override
	public void saveDataUsingChild() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//begin transaction
			tx = ses.beginTransaction();
			//prepare parent objects
			Department dept = new Department();
			dept.setDeptName("ACCOUNTS");
			dept.setDeptHead("JOHN");
			//prepare child objects
			Employee emp1 = new Employee();
			emp1.setEmpName("Suraj");
			emp1.setEmpSalary(35000.0f);
			Employee emp2 = new Employee();
			emp2.setEmpName("Yogesh");
			emp2.setEmpSalary(45000.0f);
			//set childs to parent (for one to many)
			dept.setEmployees(Set.of(emp1,emp2));
			//set parent to chis (for many to one)
			emp1.setDept(dept);
			emp2.setDept(dept);
			//save through child
			ses.save(emp1);
			ses.save(emp2);
			tx.commit();
			System.out.println("Childs to parent are saved");
		}
		catch(HibernateException he) {
			he.printStackTrace();
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Problem in saving objects (childs to parent)");
			}
		}
	}//method

	@Override
	public void loadDataUsingParent() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			//Load parents and the associated childs
			Query query = ses.createQuery("from Department");
			List<Department> list = query.getResultList();
			list.forEach(dept->{
				System.out.println("Parent :: "+dept);
				Set<Employee> childs = dept.getEmployees();
				childs.forEach(emp->{
					System.out.println("Childs :: "+emp);
				});
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//method

	/*@Override
	public void loadDataUsingChild() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			//Load parents and the associated childs
			Query query = ses.createQuery("from Employee");
			List<Employee> list = query.getResultList();
			list.forEach(emp->{
				System.out.println("Childs :: "+emp);
				Department dept = emp.getDept();
				System.out.println("Parent :: "+dept);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//method
	*/	
	@Override
	public void loadDataUsingChildWithProxy() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			//Load parents and the associated childs
			Query query = ses.createQuery("from Employee");
			List<Employee> list = query.getResultList();
			list.forEach(emp->{
				System.out.println("Childs :: "+emp);
				IDepartment dept = emp.getDept();
				System.out.println("Parent :: "+dept);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//method

}//class