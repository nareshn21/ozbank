package ozbank.ozbankapp.service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.CustomerAcc;

public interface CustomerService {
	
	public ResponseDto addNewCustomer(CustomerAcc customer);
	
	public ResponseDto deleteLoan(String customerId);
	
	

	

}
