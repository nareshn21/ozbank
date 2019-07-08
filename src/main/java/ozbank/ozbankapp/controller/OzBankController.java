package ozbank.ozbankapp.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.CustomerAcc;
import ozbank.ozbankapp.entity.CustomerLoan;
import ozbank.ozbankapp.service.CustomerService;
import ozbank.ozbankapp.service.LoanService;
import ozbank.ozbankapp.service.OfficerService;

@RestController
@RequestMapping("/ozbank")
public class OzBankController {

	@Autowired
	CustomerService customerServiceImpl;

	@Autowired
	OfficerService bankingPersonnelServiceImpl;

	@Autowired
	LoanService loanImpl;

	@PostMapping("/customer")
	public ResponseEntity<ResponseDto> addNewCustomer(@RequestBody CustomerAcc customer) {

		return new ResponseEntity<ResponseDto>(customerServiceImpl.addNewCustomer(customer), HttpStatus.CREATED);

	}

	@PostMapping("/customer/{custId}/loan")
	public ResponseEntity<ResponseDto> applyLoan(@PathVariable("customerId") String customerId,
			@RequestBody CustomerLoan loan) {
		return new ResponseEntity<ResponseDto>(loanImpl.applyLoan(customerId, loan), HttpStatus.CREATED);

	}

	@PostMapping("/officer/{officerId}/approveloan/{loanId}")
	public ResponseEntity<ResponseDto> approveLoan(@PathVariable("personnelId") Long personnelId,
			@PathVariable("loanId") Long loanId) throws ParseException {
		return new ResponseEntity<ResponseDto>(bankingPersonnelServiceImpl.approveLoan(personnelId, loanId),
				HttpStatus.CREATED);
	}


	@DeleteMapping("/customer/{custId}")
	public ResponseEntity<ResponseDto> deleteLoan(@PathVariable("customerId") String customerId) {
		return new ResponseEntity<ResponseDto>(customerServiceImpl.deleteLoan( customerId), HttpStatus.OK);

	}

}
