package com.example.airbnbbackend.repository;

import com.example.airbnbbackend.domain.HostReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserHostReviewsRepository extends JpaRepository<HostReview,Long> {

    /** userId로 Host Review 반환하는 메서드*/
    List<HostReview> findHostReviewsByUserId(Long userId);

    /** Host Review 수 조회하는 메서드*/
    @Query(value="select count(*) from host_review hr where hr.user_id=:user_id",nativeQuery = true)
    int findHostReviewsNumsByUserId(@Param("user_id") Long userId );

}
