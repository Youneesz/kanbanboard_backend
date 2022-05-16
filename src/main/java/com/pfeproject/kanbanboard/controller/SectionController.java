package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Section;
import com.pfeproject.kanbanboard.model.Session;
import com.pfeproject.kanbanboard.service.SectionService;
import com.pfeproject.kanbanboard.service.SectionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody Section input) {
        if (sectionService.updateSection(id, input) == null) {
            return "Error, couldn't update section, please recheck.";
        }
        return "Section updated successfully.";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return sectionService.deleteSection(id);
    }

    @GetMapping("/getall")
    public List<Section> getall() {
        return sectionService.getSections();
    }

    @GetMapping("/get/{id}")
    public Section get(@PathVariable int id) {
        return sectionService.getSection(id);
    }
}
