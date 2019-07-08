package ozbank.ozbankapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Officer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long officerId;
	
	private String officerDesig;
	
	@OneToMany(mappedBy="officer")
	List<CustomerLoan> loans;
	
	public List<CustomerLoan> getLoans() {
		return loans;
	}

	public void setLoans(List<CustomerLoan> loans) {
		this.loans = loans;
	}

	public Long getOfficerId() {
		return officerId;
	}

	public void setPersonnelsId(Long officerId) {
		this.officerId = officerId;
	}

	public String getOfficerDesig() {
		return officerDesig;
	}

	public void setPersonnelDesignation(String officerDesig) {
		this.officerDesig = officerDesig;
	}

		

}
