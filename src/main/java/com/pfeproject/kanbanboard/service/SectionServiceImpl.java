package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Section;
import com.pfeproject.kanbanboard.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService{

    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public static boolean containsName(List<Section> sections, String name) {
        return sections.stream().anyMatch(e -> e.getNameSection().equals(name));
    }

    public static boolean containID(List<Section> sections, int id) {
        return sections.stream().anyMatch(e -> e.getIdSection() == id);
    }

    @Override
    public List<Section> getSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSection(int id) {
        return sectionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Section updateSection(int id, Section updated) {
        return sectionRepository.save(updated);
    }

    @Override
    public String deleteSection(int id) {
        if (containID(getSections(), id)) {
            sectionRepository.deleteById(id);
            return "Section deleted.";
        }
        else {
            return "Section doesn't exist.";
        }
    }

    @Override
    public Section addSection(Section new_Section) {
        if (containsName(getSections(), new_Section.getNameSection())) {
            return null;
        }
        return sectionRepository.save(new_Section);
    }
}
