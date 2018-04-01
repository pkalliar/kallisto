package gr.mfa.efsima.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface GroupContactRepository extends JpaRepository<GroupContact, GroupContactId> {

	@Query("select u from GroupContact u where u.groupContactId.group_id =  :value")
	List<GroupContact> findByGroup(@Param("value") String value);

	@Query(value = "select * from comm.group_contacts where group_id = ?1", nativeQuery = true)
	List<GroupContact> findByGroupId(@Param("value") UUID value);


//	@Query("select u from GroupContact u where LOWER(u.legal_name) like lower(concat('%', :value, '%')) or" +
//			" lower(u.code) like lower(concat('%', :value, '%'))")
//	List<GroupContact> findByNameOrCode(@Param("value") String value);
//
//	@Query("select u from GroupContact u where LOWER(u.legal_name) like lower(concat('%', :value, '%')) or" +
//			" lower(u.code) like lower(concat('%', :value, '%'))")
//	Stream<GroupContact> findByCriteria(@Param("value") String value);

}
