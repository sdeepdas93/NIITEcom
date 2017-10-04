package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Address;

import com.niit.ecomweb1.model.User;

public interface AddressDao {
	
	public List<Address> getAllAddresses();
	public boolean insertAddress(Address address);
	public boolean deleteAddress(Address address);
	public boolean updateAddress(Address address);
	public Address getAddressByAddressId(int addressId);
	
	

}
