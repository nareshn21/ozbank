package ozbank.ozbankapp.service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.CustomerLoan;

public interface LoanService {
	
	//public ResponseDto applyLoan(String customerId);

	public ResponseDto applyLoan(String customerId, CustomerLoan loan);

}
