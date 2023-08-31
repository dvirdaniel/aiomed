package com.example.entity;

import com.example.enums.ActionType;
import com.example.enums.Status;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "TREATMENT_TASK")
public class TreatmentTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ACTION_TYPE", nullable = false)
    private ActionType actionType;

    @Column(name = "SUBJECT_PATIENT", nullable = false)
    private String subjectPatient;

    @Column(name = "START_TIME", nullable = false)
    private Timestamp startTime;

    @Column(name = "STATUS", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "TREATMENT_PLAN_ID", referencedColumnName = "ID")
    private TreatmentPlanEntity treatmentPlanEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getSubjectPatient() {
        return subjectPatient;
    }

    public void setSubjectPatient(String subjectPatient) {
        this.subjectPatient = subjectPatient;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TreatmentPlanEntity getTreatmentPlanEntity() {
        return treatmentPlanEntity;
    }

    public void setTreatmentPlanEntity(TreatmentPlanEntity treatmentPlanEntity) {
        this.treatmentPlanEntity = treatmentPlanEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreatmentTaskEntity that = (TreatmentTaskEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
