package ozbank.ozbankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ozbank.ozbankapp.entity.CustomerAcc;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerAcc, Long>{

	@Query("SELECT c.customerId FROM Customers c WHERE c.loan.loanStatus='approved'")
	List<Long> getCustomersIds();
	
	@Query("SELECT c.loan.loanStatus FROM Customers c WHERE c.customerId=:customerId")
	String getLoanStatusWhileDeleting(@Param("customerId") Long customerId);

}
