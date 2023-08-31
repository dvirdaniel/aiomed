package com.example.controller;

import com.example.entity.TreatmentPlanEntity;
import com.example.service.TreatmentPlanService;
import com.example.service.TreatmentSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TreatmentSchedulerController {

    @Autowired
    private TreatmentSchedulerService treatmentSchedulerService;

    @Autowired
    private TreatmentPlanService treatmentPlanService;

    @GetMapping("/")
    public ModelAndView getTreatmentTasks() {
        Date current = new Date();
        List<TreatmentPlanEntity> plans = treatmentPlanService.getAllByDate(current);
        List tasks = treatmentSchedulerService.calculateTreatmentTasks(plans, current);
        Map<String, List> model = new HashMap<>();
        model.put("plans", plans);
        model.put("tasks", tasks);
        return new ModelAndView("tasks", model);
    }
}
