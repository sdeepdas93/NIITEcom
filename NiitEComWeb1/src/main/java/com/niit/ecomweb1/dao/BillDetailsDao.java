package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Bill;
import com.niit.ecomweb1.model.BillDetails;

public interface BillDetailsDao {
	public boolean insertBillDetails(BillDetails billDetails);
	public boolean deleteBillDetails(BillDetails billDetails);
	public boolean updateBillDetails(BillDetails billDetails);
	public List<BillDetails> getAllBillDetails();
	public BillDetails getBillDetailsByBill(Bill bill);
	public BillDetails getBillDetailsByBillDetailsId(int billDetailsId);
	
}
