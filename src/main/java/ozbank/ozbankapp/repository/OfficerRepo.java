package ozbank.ozbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ozbank.ozbankapp.entity.Officer;

public interface OfficerRepo extends JpaRepository<Officer, Long> {

}
