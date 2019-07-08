package ozbank.ozbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ozbank.ozbankapp.entity.CustomerLoan;

public interface LoanRepo extends JpaRepository<CustomerLoan, Long> {

	@Query("SELECT l from Loans l WHERE l.customers.customerId=:customerId")
	public CustomerLoan findLoanAllreadyAppliedOrNot(@Param("customerId") Long customerId);
}
