package com.nit.dao;

public interface ICompanyDAO {
	public void loadDataUsingJoinsParentToChild();
	public void loadDataUsingJoinsChildToParent();
	
	public void loadDataUsingParentHQL();
	public void loadDataUsingJPQBC();
}
