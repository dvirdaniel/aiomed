package com.example.service;

import com.example.entity.TreatmentPlanEntity;
import com.example.entity.TreatmentTaskEntity;
import com.example.enums.Status;
import com.example.repository.TreatmentTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TreatmentTaskService {

    @Autowired
    private TreatmentTaskRepository treatmentTaskRepository;

    public List<TreatmentTaskEntity> getAll() {
        return treatmentTaskRepository.findAll();
    }

    public TreatmentTaskEntity getOne(Long id) {
        return treatmentTaskRepository.getOne(id);
    }

    public TreatmentTaskEntity addTreatmentTask(TreatmentPlanEntity treatmentPlanEntity, Date startDate) {
        TreatmentTaskEntity treatmentTaskEntity = new TreatmentTaskEntity();
        treatmentTaskEntity.setTreatmentPlanEntity(treatmentPlanEntity);
        treatmentTaskEntity.setActionType(treatmentPlanEntity.getActionType());
        treatmentTaskEntity.setStatus(Status.Active.ordinal());
        treatmentTaskEntity.setStartTime(new Timestamp(startDate.getTime()));
        treatmentTaskEntity.setSubjectPatient(treatmentPlanEntity.getSubjectPatient());

        TreatmentTaskEntity newTreatmentTaskEntity = treatmentTaskRepository.save(treatmentTaskEntity);

        return newTreatmentTaskEntity;
    }
}
