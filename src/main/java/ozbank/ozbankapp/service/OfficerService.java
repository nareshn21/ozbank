package ozbank.ozbankapp.service;

import java.text.ParseException;

import ozbank.ozbankapp.dto.ResponseDto;

public interface OfficerService {
	
	public ResponseDto approveLoan(Long officerId, Long loanId) throws ParseException;

}
