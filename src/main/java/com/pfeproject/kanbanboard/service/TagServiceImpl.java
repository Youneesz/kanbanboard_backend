package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Tag;
import com.pfeproject.kanbanboard.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public static boolean containsName(List<Tag> Tags, String name) {
        return Tags.stream().anyMatch(e -> e.getNameTag().equals(name));
    }

    public static boolean containID(List<Tag> Tags, int id) {
        return Tags.stream().anyMatch(e -> e.getIdTag() == id);
    }

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTag(int id) {
        return tagRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public String deleteTag(int id) {
        if (containID(getTags(), id)) {
            tagRepository.deleteById(id);
            return "Tag deleted.";
        }
        return "Tag doesn't exist";
    }

    @Override
    public Tag addTag(Tag new_tag) {
        if (containsName(getTags(), new_tag.getNameTag())) {
            return null;
        }
        return tagRepository.save(new_tag);
    }
}
