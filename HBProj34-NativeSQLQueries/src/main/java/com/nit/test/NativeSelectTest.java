package com.nit.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import com.nit.entity.Product;
import com.nit.utility.HibernateUtil;

public class NativeSelectTest {

	public static void main(String[] args) {

		// Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Get Session object
		Session ses = HibernateUtil.getSession();
		try (factory; ses) {
			//Entity query
			//Without mapping with entity class
			/*NativeQuery query = ses.createNativeQuery("SELECT * FROM PRODUCT");
			List<Object[]> list = query.getResultList();  
			list.forEach(record->{
				for(Object val:record) {
					System.out.print(val+" ");
				}
				System.out.println();
			});*/
			
			/*NativeQuery query = ses.createNativeQuery("SELECT * FROM PRODUCT");
			query.addEntity(Product.class);  //mapping with entity class
			List<Product> list = query.getResultList();
			list.forEach(prod->{
				System.out.println(prod.getPid()+" "+prod.getPname()+" "+prod.getPrice()+" "+prod.getQty());
			});*/
			
			//Scalar query (getting specific multiple column values)
			/*NativeQuery query = ses.createNativeQuery("SELECT PID,PRICE FROM PRODUCT WHERE PRICE BETWEEN ? AND ?");
			//map scalar query results with hibernate data types
			query.addScalar("PID", StandardBasicTypes.INTEGER);
			query.addScalar("PRICE", StandardBasicTypes.FLOAT);
			//set query param values
			query.setParameter(1, 1000.0f);
			query.setParameter(2, 20000.0f);
			//execute the query
			List<Object[]> list = query.getResultList();
			list.forEach(row->{
				for(Object val:row) {
					System.out.print(val+" ");
				}
				System.out.println();
			});*/
			
			//Scalar query (getting specific single column values)
			/*NativeQuery query = ses.createNativeQuery("SELECT PID FROM PRODUCT WHERE QTY>=?1");
			//map scalar query results with hibernate data types
			query.addScalar("PID", StandardBasicTypes.INTEGER);
			//set query param values
			query.setParameter(1, 2);
			//execute the query
			List<Integer> list = query.getResultList();
			list.forEach(id->{
				System.out.println(id);
			});*/
			
			//Native SQL Entity query with named params
			NativeQuery query = ses.createNativeQuery("SELECT * FROM PRODUCT WHERE PRICE>=:priceRange AND QTY>=:qtyRange");
			//map entity query results with entity class
			query.addEntity(Product.class);
			//Set query param values
			query.setParameter("priceRange", 10000.0f);
			query.setParameter("qtyRange", 2.0f);
			//execute the query
			List<Product> list = query.getResultList();
			//Process the results
			list.forEach(prod->{
				System.out.println(prod);
			});
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}// main
}// class