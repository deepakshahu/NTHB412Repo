package com.nit.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nit.entity.Employee;
import com.nit.utility.HibernateUtil;

public class CompanyDAOImpl implements ICompanyDAO {

	@Override
	public void saveData() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(ses){
			//Read image file content to byte[]
			File file1 = new File("E:\\Deepak\\Classcontent\\Deepak.jpg");
			InputStream is = new FileInputStream(file1);
			byte[] imageContent = new byte[(int) file1.length()];
			is.read(imageContent);

			//Read text file content to char[]
			File file2 = new File("E:\\Deepak\\Classcontent\\Resume.txt");
			Reader reader = new FileReader(file2);			
			char[] resumeContent = new char[(int) file2.length()];
			reader.read(resumeContent);

			//Prepare entity class object
			Employee emp = new Employee();
			emp.setEmpName("Deepak");
			emp.setEmpSalary(95000.0f);
			emp.setEmpPhoto(imageContent);
			emp.setEmpResume(resumeContent);

			//begin transaction
			tx = ses.beginTransaction();
			int idVal = (int) ses.save(emp);
			tx.commit();		
			System.out.println("Object is saved with id value :: "+idVal);
		}
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved");
			}
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//method

	@Override
	public void loadData() {
		Session ses = HibernateUtil.getSession();
		try(ses){
			Employee emp = ses.get(Employee.class, 1000);
			if(emp==null) {
				System.out.println("Employee not found");
				return;
			}
			else {
				System.out.println(emp.getEmpNo()+" "+emp.getEmpName()+" "+emp.getEmpSalary());
				byte[] photoContent = emp.getEmpPhoto();
				char[] resumeContent = emp.getEmpResume();
				//Create OutputStream pointing to destination file
				OutputStream os = new FileOutputStream("new_pic.jpeg");
				os.write(photoContent);
				os.flush();
				os.close();
				
				//Create Writer Stream pointing to destination file
				Writer writer = new FileWriter("new_resume.txt");
				writer.write(resumeContent);
				writer.flush();
				writer.close();
				System.out.println("LOBs are retrieved successfully");
			}
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//method

}//class