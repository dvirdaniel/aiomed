package com.example.repository;

import com.example.entity.TreatmentTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentTaskRepository extends JpaRepository<TreatmentTaskEntity, Long> {

}
