package com.nit.test;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class PaginationTest1 {

	public static void main(String[] args) {
		
		//Read inputs from end user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter page size :: ");
		int pageSize = sc.nextInt();
		
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//Get Session object
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			
			//Get total records in the db table
			Query query1 = ses.createQuery("select count(*) from Product");
			long totalRecords = (long) query1.getSingleResult();
			long pagesCount = totalRecords/pageSize;
			if(totalRecords%pageSize!=0)
				pagesCount++;
 			//Create HQL query for pagination
			Query query2 = ses.createQuery("from Product");
			for(int pageNo = 1; pageNo <= pagesCount; ++pageNo) {
				System.out.println(pageNo+"/"+pagesCount+" page records");
				//get startPosition for each page
				int startPosition = (pageNo*pageSize)-pageSize;
				//set pagination parameter
				query2.setFirstResult(startPosition);
				query2.setMaxResults(pageSize);
				//execute the query
				List<Product> list = query2.getResultList();
				//Process the result
				list.forEach(System.out::println);
				System.out.println("====================================================");
			}
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}//main
}//class