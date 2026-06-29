package com.fantime.fantime_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fantime.fantime_backend.entity.Schedule;
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    
}
