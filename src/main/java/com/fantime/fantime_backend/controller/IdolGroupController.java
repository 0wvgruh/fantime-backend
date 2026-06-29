package com.fantime.fantime_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.fantime.fantime_backend.entity.IdolGroup;
import com.fantime.fantime_backend.service.IdolGroupService;


@RestController
@RequestMapping("/api/groups")
public class IdolGroupController {

    private final IdolGroupService idolGroupService;

    public IdolGroupController(IdolGroupService idolGroupService) {
        this.idolGroupService = idolGroupService;
    }

    @GetMapping
    public List<IdolGroup> getAllIdolGroups() {
        return idolGroupService.getAllIdolGroups();
    }
}
