package ozbank.ozbankapp.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.CustomerAcc;
import ozbank.ozbankapp.entity.CustomerLoan;
import ozbank.ozbankapp.entity.Emi;
import ozbank.ozbankapp.entity.Officer;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.EmiRepo;
import ozbank.ozbankapp.repository.LoanRepo;
import ozbank.ozbankapp.repository.OfficerRepo;

@Service
public class OfficerServiceImpl implements OfficerService {

	@Autowired
	OfficerRepo officerRepo;
	@Autowired
	LoanRepo loanRepo;
	@Autowired
	EmiRepo emiRepo;

	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public ResponseDto approveLoan(Long officerId, Long loanId) throws ParseException {
		Officer officer;
		CustomerLoan loan;

		List<Emi> lstEmi = emiRepo.isAlreadySetOrNot(loanId);
		if (lstEmi != null && !lstEmi.isEmpty())
			throw new OzBankException("Emi for this Particular LoanId is Already set ...");

		try {
			officer = officerRepo.findById(officerId).get();
			loan = loanRepo.findById(loanId).get();
		} catch (NoSuchElementException ex) {
			throw new OzBankException("Please provide valid PersonnelId/LoanId");
		}
		CustomerAcc customer = loan.getCustomers();
		Date currentDate = new Date();
		int age = currentDate.getYear() - customer.getCustomerDob().getYear();
		if ("deleted".equalsIgnoreCase(String.valueOf(loan.getLoanStatus())))
			throw new OzBankException("This Customer's Loan is already Deleted...");
		else if (!"officer".equalsIgnoreCase(officer.getOfficerDesig()))
			throw new OzBankException("Only Officer can Approve the Loan... ");
		else if (customer.getCustCreditScore() <= 900)
			throw new OzBankException(" Credit Score is Low...!!");

		else if (customer.getCustExpInMonths() <= 24)
			throw new OzBankException("Work Experience is Less..");
		else if (!(age > 22 && age < 55))
			throw new OzBankException("Age criteria is not matchings ");
		loan.setLoanStatus("Approved");
		loanRepo.save(loan);
		
		Date nextPaymentDate = setPaymentDate();
		int counter = 1;
		
		return new ResponseDto("Loan is Approved");
	}

	private Date setPaymentDate() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		String basePayDate = "05/Jul/2019";
		Date payDate = formatter.parse(basePayDate);

		Date paymentDate = formatter.parse(formatter.format(payDate));

		return paymentDate;

	}

	

}
