package com.pankal.protocol;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

//	@Query("select u from Protocol u where LOWER(u.display_name) like lower(concat('%', :value, '%')) or" +
//			" lower(u.code) like lower(concat('%', :value, '%'))")
//	List<Protocol> findByNameOrCode(@Param("value") String value);
//
//	@Query("select u from Protocol u where LOWER(u.display_name) like lower(concat('%', :value, '%')) or" +
//			" lower(u.code) like lower(concat('%', :value, '%'))")
//	Stream<Protocol> findByCriteria(@Param("value") String value);


    @Query("select u from Protocol u where category = 'incoming' order by prot_date desc")
    List<Protocol>  findAllIncoming();

    List<Protocol>  findByCategoryOrderByProtdateDesc(String category);

    Stream<Protocol>  findByThemeLikeAndCategoryOrderByProtdateDesc(String theme, String category);


}