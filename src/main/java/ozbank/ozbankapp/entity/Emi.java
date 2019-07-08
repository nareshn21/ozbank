package ozbank.ozbankapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Emi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emiId;

	private Double emiIntrestRate;
	private Double monthylyEmi;
	private Double pendingAmount;

	@Temporal(TemporalType.DATE)
	private Date emiDueDate;

	public Date getEmiDueDate() {
		return emiDueDate;
	}

	public void setEmiPaymentDate(Date emiDueDate) {
		this.emiDueDate = emiDueDate;
	}

	@JsonBackReference
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "Loan_Id")
	CustomerLoan loans;

	public Long getEmiId() {
		return emiId;
	}

	public void setEmiId(Long emiId) {
		this.emiId = emiId;
	}

	public Double getEmiIntrestRate() {
		return emiIntrestRate;
	}

	public void setEmiIntrestRate(Double emiIntrestRate) {
		this.emiIntrestRate = emiIntrestRate;
	}

	public Double getMonthylyEmi() {
		return monthylyEmi;
	}

	public void setMonthylyEmi(Double monthylyEmi) {
		this.monthylyEmi = monthylyEmi;
	}

	public Double getPendingAmount() {
		return pendingAmount;
	}

	public void setRestAmountOfLoan(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public CustomerLoan getLoans() {
		return loans;
	}

	public void setLoans(CustomerLoan loans) {
		this.loans = loans;
	}

}
