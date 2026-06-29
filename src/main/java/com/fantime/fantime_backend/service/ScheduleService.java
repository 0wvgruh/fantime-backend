package com.fantime.fantime_backend.service;
import com.fantime.fantime_backend.repository.ScheduleRepository;
import com.fantime.fantime_backend.entity.Schedule;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
