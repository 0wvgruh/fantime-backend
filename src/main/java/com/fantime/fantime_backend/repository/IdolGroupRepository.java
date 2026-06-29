package com.fantime.fantime_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fantime.fantime_backend.entity.IdolGroup;

public interface IdolGroupRepository extends JpaRepository<IdolGroup, Long> {
    
}
