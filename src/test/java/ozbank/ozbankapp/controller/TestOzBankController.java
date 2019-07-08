package ozbank.ozbankapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ozbank.ozbankapp.entity.CustomerAcc;
import ozbank.ozbankapp.entity.CustomerLoan;
import ozbank.ozbankapp.service.OfficerServiceImpl;
import ozbank.ozbankapp.service.CustomerServiceImpl;
import ozbank.ozbankapp.service.LoanServiceImpl;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TestOzBankController {

	@InjectMocks
	private OzBankController ozBankController;

	private MockMvc mockmvc;

	CustomerAcc customer;
	CustomerLoan loan;

	@Mock
	CustomerServiceImpl customerServiceImpl;
	@Mock
	LoanServiceImpl loanServiceImpl;
	@Mock
	OfficerServiceImpl bankingPersonnelServiceImpl;

	@Before
	public void setUp() {

		mockmvc = MockMvcBuilders.standaloneSetup(ozBankController).build();
		customer = new CustomerAcc();
		customer.setCustCreditScore(980);
		customer.setCustExpInMonths(32);
		customer.setCustomerName("PK");

		loan = new CustomerLoan();
		loan.setLoanAmount(3444.44);
		loan.setLoanStatus("Pending");
	}

	@Test
	public void testAddNewCustomer() throws Exception {
		mockmvc.perform(
				MockMvcRequestBuilders.post("/api/ozbank/customers/addnew").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
				.andExpect(status().is(201));

	}

	@Test
	public void testApplyLoan() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.post("/api/ozbank/customers/{customerId}/loan/applyloan", 1)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(loan)))
				.andExpect(status().is(201));

	}

	@Test
	public void testApproveLoan() throws Exception {
		mockmvc.perform(
				MockMvcRequestBuilders.post("/api/ozbank/bankpersonnel/{personnelId}/approveloan/{loanId}", 100, 1))
				.andExpect(status().is(201));

	}

	@Test
	public void testEmiDetails() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.get("/api/ozbank/customers/{customerId}/emi", 2))
				.andExpect(status().is(200));

	}
	@Test
	public void testEmiDetails1() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.get("/api/ozbank/customers/emi", 2))
				.andExpect(status().is(200));

	}
	@Test
	public void testDeleteLoan() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.delete("/api/ozbank/customers/{customerId}/delete", 2))
				.andExpect(status().is(200));

	}

	public static String asJsonString(final Object obj1) {
		try {
			ObjectMapper obj = new ObjectMapper();
			String custJson = obj.writeValueAsString(obj1);
			return custJson;// new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
