package com.nit.dao;

public interface IOneToOnePKDAO {
	public void saveDataUsingParent();
	public void saveDataUsingChild();
	public void loadDataUsingParent();
	public void loadDataUsingChild();
}
