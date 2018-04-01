package com.pankal.affair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface AffairRepository extends JpaRepository<Affair, Long> {

	@Query("select u from Affair u where LOWER(u.display_name) like lower(concat('%', :value, '%')) or" +
			" lower(u.code) like lower(concat('%', :value, '%'))")
	List<Affair> findByNameOrCode(@Param("value") String value);

	@Query("select u from Affair u where LOWER(u.display_name) like lower(concat('%', :value, '%')) or" +
			" lower(u.code) like lower(concat('%', :value, '%'))")
	Stream<Affair> findByCriteria(@Param("value") String value);

}