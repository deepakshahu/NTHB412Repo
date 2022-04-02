package com.nit.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nit.entity.PersonDetails;
import com.nit.utility.HibernateUtil;

public class CollectionMappingSelectTest {

	public static void main(String[] args) {
		//Create SessionFactory, Session object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = HibernateUtil.getSession();
		try(factory;ses){
			//Execute the HQL query
			Query query = ses.createQuery("from PersonDetails");
			List<PersonDetails> list = query.getResultList();
			list.forEach(pd->{
				System.out.println("Person Info :: "+pd.getPid()+" "+pd.getPname()+" "+pd.getPaddrs());
				//NickNames Info
				List<String> nickNames = pd.getNickNames();
				System.out.println("NickNames are :: "+nickNames.toString());
				//Friends Info
				List<String> friends = pd.getFriends();
				System.out.println("Friends are :: "+friends.toString());
				//ContactNumbers Info
				Set<Long> contactNumbers = pd.getContactNumbers();
				System.out.println("ContactNumbers are :: "+contactNumbers);
				//Id Details Info
				Map<String,Long> idDetails = pd.getIdDetails();
				System.out.println("Id Details are :: "+idDetails);
			});
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
			
	}//main
}//class