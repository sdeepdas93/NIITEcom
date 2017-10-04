package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Supplier;

public interface SupplierDao {
	public boolean insertSupplier(Supplier supplier);
	public boolean deleteSupplier(Supplier supplier);
	public boolean updateSupplier(Supplier supplier);
	public List<Supplier> getAllSuppliers();

}
