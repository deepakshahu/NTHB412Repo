package com.nit.test;

import java.time.LocalDateTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nit.entity.CardPayment;
import com.nit.entity.ChequePayment;
import com.nit.utility.HibernateUtil;

public class TPCH_Anno_InheritanceMappingInsertTest {

	public static void main(String[] args) {
		//Get SessionFactory, Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try(factory;ses){
			//begin transaction
			tx = ses.beginTransaction();
			//prepare objects
			ChequePayment cheque = new ChequePayment();
			cheque.setAmount(45677.0);
			cheque.setTxDate(LocalDateTime.of(2022, 8, 25, 12, 55, 43));
			cheque.setChequeNo(124578963L);
			cheque.setChequeType("SELF");

			CardPayment card = new CardPayment();
			card.setAmount(25415.0);
			card.setTxDate(LocalDateTime.of(2022, 9, 26, 11, 45, 35));
			card.setCardNo(2587469853L);
			card.setCardType("CREDIT");
			card.setGateway("VISA");

			//save objects
			ses.save(cheque);
			ses.save(card);

			tx.commit();
			System.out.println("Objects are saved");
		}
		catch(HibernateException he) {
			if(tx!=null && tx.getStatus()!=null && tx.getRollbackOnly()) {
				System.out.println("Problem in saving objects");
				tx.rollback();
			}
			he.printStackTrace();
		}
		
	}//main
}//class