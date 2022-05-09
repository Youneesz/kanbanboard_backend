package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Section;

import java.util.List;

public interface SectionService {
    public List<Section> getSections();
    public Section getSection(int id);
    public Section updateSection(int id, Section updated);
    public String deleteSection(int id);
    public Section addSection(Section new_Section);
}
