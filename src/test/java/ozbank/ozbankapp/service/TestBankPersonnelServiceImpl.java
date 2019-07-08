package ozbank.ozbankapp.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import ozbank.ozbankapp.entity.CustomerLoan;
import ozbank.ozbankapp.entity.Emi;
import ozbank.ozbankapp.entity.Officer;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.EmiRepo;
import ozbank.ozbankapp.repository.LoanRepo;
import ozbank.ozbankapp.repository.OfficerRepo;

@RunWith(SpringRunner.class)
public class TestBankPersonnelServiceImpl {

	@InjectMocks
	OfficerServiceImpl bankPersonnelServiceImpl = new OfficerServiceImpl();

	@Mock
	OfficerRepo officerRepo;
	@Mock
	LoanRepo loanRepo;
	@Mock
	EmiRepo emiRepo;
	
	@Mock
	Officer officer;
	
	@Mock
	CustomerLoan loan;
	
	@Test(expected = OzBankException.class)
	public void testApproveLoan() throws ParseException {
		
		Emi emi=new Emi();
		emi.setEmiId(1L);
		emi.setEmiIntrestRate(12.0);
		emi.setMonthylyEmi(5000.0);
		List<Emi> emiList=new ArrayList<>();
			
		Mockito.when(emiRepo.isAlreadySetOrNot(Mockito.anyLong())).thenReturn(emiList);
		bankPersonnelServiceImpl.approveLoan(100L, 1L);
		
		
	}
	@Test(expected = OzBankException.class)
	public void testApproveLoan1() throws ParseException {
		
		Emi emi=new Emi();
		emi.setEmiId(1L);
		emi.setEmiIntrestRate(12.0);
		emi.setMonthylyEmi(5000.0);
		List<Emi> emiList=new ArrayList<>();
		emiList.add(emi);
		
		Officer bankingPersonnels=new Officer();
		bankingPersonnels.setPersonnelDesignation("officer");
		bankingPersonnels.setPersonnelsId(100L);
		
	
		loan.setLoanId(1L);
		loan.setLoanAmount(6000000.0);
		
		
		Mockito.when(emiRepo.isAlreadySetOrNot(Mockito.anyLong())).thenReturn(emiList);
		Mockito.when(officerRepo.findById(Mockito.anyLong()).get()).thenReturn(bankingPersonnels);
		Mockito.when(loanRepo.findById(Mockito.anyLong()).get()).thenReturn(loan);
		bankPersonnelServiceImpl.approveLoan(100L, 1L);
		
		
	}
	@Test
	public void testApproveLoan2() throws ParseException {
		
		Emi emi=new Emi();
		emi.setEmiId(1L);
		emi.setEmiIntrestRate(12.0);
		emi.setMonthylyEmi(5000.0);
		List<Emi> emiList=new ArrayList<>();
		emiList.add(emi);
		
		Officer b1=new Officer();
		b1.setPersonnelDesignation("officer");
		b1.setPersonnelsId(100L);
		
		CustomerLoan loan=new CustomerLoan();
		loan.setLoanId(1L);
		loan.setLoanAmount(6000000.0);
		
		
		Mockito.when(emiRepo.isAlreadySetOrNot(Mockito.anyLong())).thenReturn(emiList);
		Mockito.when(officerRepo.findById(Mockito.anyLong()).get()).thenReturn(b1);
		Mockito.when(loanRepo.findById(Mockito.anyLong()).get()).thenReturn(loan);
		bankPersonnelServiceImpl.approveLoan(100L, 1L);
		
		
	}


}
