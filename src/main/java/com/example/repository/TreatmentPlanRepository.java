package com.example.repository;

import com.example.entity.TreatmentPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlanEntity, Long> {

    @Query("SELECT p FROM TreatmentPlanEntity p where p.startTime <= :current AND (p.endTime >= :current OR p.endTime IS NULL)")
    List<TreatmentPlanEntity> findAllByDate(Date current);
}
