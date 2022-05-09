package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Section;
import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.service.SectionService;
import com.pfeproject.kanbanboard.service.SectionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sections")
@CrossOrigin
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionServiceImpl sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/add")
    public Section add(@RequestBody Section section) {
        return sectionService.addSection(section);
    }
}
