package com.pfeproject.kanbanboard.controller;

import com.pfeproject.kanbanboard.model.Tag;
import com.pfeproject.kanbanboard.service.TagService;
import com.pfeproject.kanbanboard.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tags")
@CrossOrigin
public class TagController {
    @Autowired
    private final TagService tagService;

    public TagController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public Tag add(@RequestBody Tag t) {
        return tagService.addTag(t);
    }

    @PutMapping("/update/{id}")
    public Tag update(@PathVariable int id, @RequestBody Tag tag) {
        Tag modified = tagService.getTag(id);
        modified.setNameTag(tag.getNameTag());
        modified.setTags_taches(tag.getTags_taches());
        return tagService.addTag(modified);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return tagService.deleteTag(id);
    }

    @GetMapping("/getall")
    public List<Tag> getAll() {
        return tagService.getTags();
    }

    @GetMapping("/get/{id}")
    public Tag get(@PathVariable int id) {
        return tagService.getTag(id);
    }
}
