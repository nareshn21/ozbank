package ozbank.ozbankapp.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.CustomerAcc;
import ozbank.ozbankapp.entity.CustomerLoan;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.CustomerRepo;
import ozbank.ozbankapp.repository.LoanRepo;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepo loanRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Override
	public ResponseDto applyLoan(String customerId, CustomerLoan loan) {
		// TODO Auto-generated method stub
		Long custId;
		try {
			custId = Long.parseLong(customerId);
		} catch (NumberFormatException ex) {
			throw new OzBankException("Please Provide a Valid Customer Id...");
		}

		CustomerLoan loanRec = loanRepo.findLoanAllreadyAppliedOrNot(custId);
		if (loanRec != null) {
			if ("Pending".equalsIgnoreCase(loanRec.getLoanStatus())) {
				throw new OzBankException("Loan Status is Pending !! This Customer is under investigation for Loan apporve...");
			} else if ("Rejected".equalsIgnoreCase(loanRec.getLoanStatus())
					|| "Deleted".equalsIgnoreCase(loanRec.getLoanStatus())) {
				throw new OzBankException("This Customer should apply for for the Loan Again,Because it's Loan Status is REJECTED/DELETED");
			} else {
				throw new OzBankException("This Customer's Loan is already approved...");
			}
		} else {
			try {
				CustomerAcc customer = customerRepo.findById(custId).get();
				// if (customer == null)
				// throw new OzBankException("This Customer is not available...");
				loan.setCustomers(customer);
				loan.setLoanIntrestRate(12.0);
				loan.setLoanStatus("Pending");
				loanRepo.save(loan);
			} catch (NoSuchElementException ex) {
				throw new OzBankException("This Customer is not available...");
			}
		}
		// throw new OzBankException("This Customer already apply for the");
		return new ResponseDto("Loan Approval is in progress for the Customer...");

	}

}
