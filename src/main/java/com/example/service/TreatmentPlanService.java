package com.example.service;

import com.example.entity.TreatmentPlanEntity;
import com.example.repository.TreatmentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TreatmentPlanService {


    @Autowired
    private TreatmentPlanRepository treatmentPlanRepository;

    public List<TreatmentPlanEntity> getAll() {
        return treatmentPlanRepository.findAll();
    }

    public List<TreatmentPlanEntity> getAllByDate(Date current) {
        return treatmentPlanRepository.findAllByDate(current);
    }

}
