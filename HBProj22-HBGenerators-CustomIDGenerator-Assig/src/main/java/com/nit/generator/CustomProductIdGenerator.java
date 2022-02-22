package com.nit.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomProductIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String prefix = "NIT";
		Serializable result = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = session.connection();
			statement=connection.createStatement();
			resultSet=statement.executeQuery("select count(PID) as Id from Product_CustomID");

			if(resultSet.next())
			{
				int nextValue = resultSet.getInt(1);
				String suffix = String.format("%03d", nextValue);
				result = prefix.concat(suffix);
				System.out.println("Custom generated sequence is : " + result);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}//class