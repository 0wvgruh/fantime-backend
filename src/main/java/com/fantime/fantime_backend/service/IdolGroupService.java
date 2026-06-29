package com.fantime.fantime_backend.service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.fantime.fantime_backend.entity.IdolGroup;
import com.fantime.fantime_backend.repository.IdolGroupRepository;
@Service
public class IdolGroupService {
    private final IdolGroupRepository idolGroupRepository;

    public IdolGroupService(IdolGroupRepository idolGroupRepository) {
        this.idolGroupRepository = idolGroupRepository;
    }
    public List<IdolGroup> getAllIdolGroups() {
        return idolGroupRepository.findAll();
    }
}
