package com.example.service;

import com.example.entity.TreatmentPlanEntity;
import com.example.entity.TreatmentTaskEntity;
import com.example.enums.RepetitionType;
import com.example.enums.WeekDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TreatmentSchedulerService {

    @Autowired
    private TreatmentTaskService treatmentTaskService;

    private List<Date> calculateTreatmentDates(TreatmentPlanEntity plan) {
        List<Date> dates = new ArrayList<>();
        boolean repetition = false;
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        Date startTimestamp = plan.getStartTime();
        Date endTimestamp = plan.getEndTime();
        String recurrencePattern = plan.getRecurrencePattern();
        int dayOfWeek = 0;
        String startTime = "";
        String endTime = "";
        if (recurrencePattern != null) {
            String[] parts = recurrencePattern.split(" ");
            if (parts[0] != null && parts[0].toLowerCase().equals("every")) {
                repetition = true;
            }
            if (parts[1] != null) {
                WeekDay weekDay = WeekDay.getWeekDay(parts[1]);
                if (weekDay != null) {
                    dayOfWeek = weekDay.ordinal() + 1;
                    calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                     if (calendar.getTime().compareTo(current) < 1) {
                        calendar.add(Calendar.WEEK_OF_YEAR, 1);
                    }
                } else {
                    RepetitionType repetitionType = RepetitionType.getRepetitionType(parts[1]);
                    if (repetitionType != null) {
                        switch (repetitionType) {
                            case day -> calendar.add(Calendar.DATE, 1);
                            case week -> calendar.add(Calendar.WEEK_OF_YEAR, 1);
                            case month -> calendar.add(Calendar.WEEK_OF_MONTH, 1);
                            case year -> calendar.add(Calendar.DAY_OF_YEAR, 1);
                        }
                    }
                }
            }
            if (parts[2] != null && parts[2].toLowerCase().equals("at")) {

            }
            if (parts[3] != null) {
                startTime = parts[3].toLowerCase();
            }
            if (parts.length > 4 && parts[4] != null && parts[4].toLowerCase().equals("and")) {

            }
            if (parts.length > 5 && parts[5] != null) {
                endTime = parts[5].toLowerCase();
            }
        }

        if (dayOfWeek > 0) {
            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        }
        if (!startTime.isEmpty()) {
            int hours = Integer.valueOf(startTime.split(":")[0]);
            calendar.set(Calendar.HOUR_OF_DAY, hours);
            int minutes = Integer.valueOf(startTime.split(":")[1]);
            calendar.set(Calendar.MINUTE, minutes);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
        System.out.println(calendar.getTime());
        System.out.println(current);

        dates.add(calendar.getTime());

        return dates;
    }

    public List calculateTreatmentTasks(List<TreatmentPlanEntity> plans, Date current) {
        List<TreatmentTaskEntity> tasks = new ArrayList<>();
        plans.forEach( plan -> {
            List<Date> calculateDates = calculateTreatmentDates(plan);
            if (calculateDates != null) {
                calculateDates.forEach( date -> {
                    tasks.add(treatmentTaskService.addTreatmentTask(plan, date));
                  }
                );
            }
        });

        return tasks;
    }
}
