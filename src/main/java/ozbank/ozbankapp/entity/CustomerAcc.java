package ozbank.ozbankapp.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CustomerAcc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String custName;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date custDob;

	private int custExpInMonths;
	private int custCreditScore;

	@OneToOne(mappedBy = "customers")
	private CustomerLoan loan;

	public CustomerLoan getLoan() {
		return loan;
	}

	public void setLoan(CustomerLoan loan) {
		this.loan = loan;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustomerName(String custName) {
		this.custName = custName;
	}

	public Date getCustomerDob() {
		return custDob;
	}

	public void setCustomerDob(Date custDob) {
		this.custDob = custDob;
	}

	public int getCustExpInMonths() {
		return custExpInMonths;
	}

	public void setCustExpInMonths(int custExpInMonths) {
		this.custExpInMonths = custExpInMonths;
	}

	public int getCustCreditScore() {
		return custCreditScore;
	}

	public void setCustCreditScore(int custCreditScore) {
		this.custCreditScore = custCreditScore;
	}

}
