package com.nit.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.CardPayment;
import com.nit.entity.ChequePayment;
import com.nit.entity.Payment;
import com.nit.utility.HibernateUtil;

public class TPCC_Anno_InheritanceMappingSelectTest {

	public static void main(String[] args) {
		//Get SessionFactory, Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//execute HQL query
			Query query1 = ses.createQuery("from ChequePayment");
			List<ChequePayment> list1 = query1.getResultList();
			list1.forEach(cheque->{
				System.out.println(cheque);
			});
			System.out.println("===============================");
			Query query2 = ses.createQuery("from CardPayment");
			List<CardPayment> list2 = query2.getResultList();
			list2.forEach(card->{
				System.out.println(card);
			});
			System.out.println("===============================");
			Query query3 = ses.createQuery("from Payment");
			List<Payment> list3 = query3.getResultList();
			list3.forEach(pay->{
				System.out.println(pay);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
	}

}
