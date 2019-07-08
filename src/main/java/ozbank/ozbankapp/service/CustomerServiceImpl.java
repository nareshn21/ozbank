package ozbank.ozbankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.CustomerAcc;
import ozbank.ozbankapp.entity.CustomerLoan;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.CustomerRepo;
import ozbank.ozbankapp.repository.EmiRepo;
import ozbank.ozbankapp.repository.LoanRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	LoanRepo loanRepo;

	@Autowired
	EmiRepo emiRepo;

	@Override 
	public ResponseDto addNewCustomer(CustomerAcc customer) { 	
	  customerRepo.save(customer); 
	  return new
	  ResponseDto("Customer Added SuccessFully in the Database..."); }

		@Override
	public ResponseDto deleteLoan(String customerId) {

		Long custId;
		try {
			custId = Long.parseLong(customerId);
		} catch (NumberFormatException ex) {
			throw new OzBankException("Please provide valid Customer Id");
		}
		String loanStatusOfCustomer = customerRepo.getLoanStatusWhileDeleting(custId);
		if (!"approved".equalsIgnoreCase(String.valueOf(loanStatusOfCustomer))) {
			CustomerLoan loan = loanRepo.findLoanAllreadyAppliedOrNot(custId);
			if (loan == null) {
				throw new OzBankException("Customer doesn't apply for the Loan yet...");
			}
			loan.setLoanStatus("Deleted");
			loanRepo.save(loan);
			return new ResponseDto("Customer Id:" + custId + " is Deleted ");
		} else {
			throw new OzBankException("Customer's Emi is Running Can't be deleted...");
		}
		
	}

}
