package com.nit.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nit.entity.Department;
import com.nit.entity.Employee;
import com.nit.utility.HibernateUtil;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void loadDataUsingJoinsParentToChild() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			Query query = ses.createQuery("select d.deptNo,d.deptName,d.deptHead,e.empNo,e.empName,e.empSalary from Department d inner join d.employees e");
			List<Object[]> list = query.getResultList();
			list.forEach(row->{
				for(Object val:row) {
					System.out.print(val+" ");
				}
				System.out.println();
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadDataUsingJoinsChildToParent() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			//Query query = ses.createQuery("select e.empNo,e.empName,e.empSalary,d.deptNo,d.deptName,d.deptHead from Employee e inner join e.dept d");
			//Query query = ses.createQuery("select e.empNo,e.empName,e.empSalary,d.deptNo,d.deptName,d.deptHead from Employee e left join e.dept d");
			//Query query = ses.createQuery("select e.empNo,e.empName,e.empSalary,d.deptNo,d.deptName,d.deptHead from Employee e right join e.dept d");
			Query query = ses.createQuery("select e.empNo,e.empName,e.empSalary,d.deptNo,d.deptName,d.deptHead from Employee e full join e.dept d");
			List<Object[]> list = query.getResultList();
			list.forEach(row->{
				for(Object val:row) {
					System.out.print(val+" ");
				}
				System.out.println();
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadDataUsingParentHQL() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			Query query = ses.createQuery("From Department");
			List<Department> list = query.getResultList();
			list.forEach(dept->{
				System.out.println("Parent :: "+dept);
				Set<Employee> childs = dept.getEmployees();
				childs.forEach(emp->{
					System.out.println("Child :: "+emp);
				});
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//method

	@Override
	public void loadDataUsingJPQBC() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			//Create CriteriaBuilder object
			CriteriaBuilder ctBuilder = ses.getCriteriaBuilder();
			//Create CriteriaQuery object
			CriteriaQuery<Department> ctQuery = ctBuilder.createQuery(Department.class);
			//Create Root object specifying the from operation
			Root<Department> root = ctQuery.from(Department.class);  //SELECT * FROM PRODUCT
			root.fetch("employees", JoinType.INNER);
			//Create Query object having CriteriaQuery object
			Query query = ses.createQuery(ctQuery);
			//execute the QBC logic
			List<Department> list = query.getResultList();
			//Process the Result
			list.forEach(dept->{
				System.out.println("Parent :: "+dept);
				//get childs
				Set<Employee> childs = dept.getEmployees();
				childs.forEach(emp->{
					System.out.println("Child :: "+emp);
				});
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}

}//class