package gr.mfa.efsima.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface ContactGroupRepository extends JpaRepository<ContactGroup, Long> {

	@Query("select u from ContactGroup u where LOWER(u.name) like lower(concat('%', :value, '%')) or" +
			" lower(u.code) like lower(concat('%', :value, '%'))")
	List<ContactGroup> findByNameOrCode(@Param("value") String value);



	@Query("select u from ContactGroup u where LOWER(u.name) like lower(concat('%', :value, '%')) or" +
			" lower(u.code) like lower(concat('%', :value, '%'))")
	Stream<ContactGroup> findByCriteria(@Param("value") String value);

}
