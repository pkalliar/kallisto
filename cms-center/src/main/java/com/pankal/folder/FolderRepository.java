package com.pankal.folder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface FolderRepository extends JpaRepository<Folder, Long> {

	@Query("select u from Folder u where LOWER(u.display_name) like lower(concat('%', :value, '%')) or" +
			" lower(u.code) like lower(concat('%', :value, '%'))")
	List<Folder> findByNameOrCode(@Param("value") String value);

	@Query("select u from Folder u where LOWER(u.display_name) like lower(concat('%', :value, '%')) or" +
			" lower(u.code) like lower(concat('%', :value, '%'))")
	Stream<Folder> findByCriteria(@Param("value") String value);

}
