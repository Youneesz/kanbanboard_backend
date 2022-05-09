package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Tag;

import java.util.List;

public interface TagService {
    public List<Tag> getTags();
    public Tag getTag(int id);
    public String deleteTag(int id);
    public Tag addTag(Tag new_tag);
}
