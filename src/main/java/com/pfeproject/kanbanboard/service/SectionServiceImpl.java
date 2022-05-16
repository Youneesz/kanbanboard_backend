package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Section;
import com.pfeproject.kanbanboard.repository.SectionRepository;
import com.pfeproject.kanbanboard.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private final SectionRepository sectionRepository;

    @Autowired
    private final SessionRepository sessionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository, SessionRepository sessionRepository) {
        this.sectionRepository = sectionRepository;
        this.sessionRepository = sessionRepository;
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
        Section nv = getSection(id);
        nv.setNameSection(updated.getNameSection());
        nv.setSession(updated.getSession());
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
        if (containsName(getSections(), new_Section.getNameSection()) || sessionRepository.findAll().stream().noneMatch(e -> e.getIdSession() == new_Section.getSession().getIdSession())) {
            return null;
        }
        return sectionRepository.save(new_Section);
    }
}
