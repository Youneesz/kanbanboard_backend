package com.pfeproject.kanbanboard.service;

import com.pfeproject.kanbanboard.model.Section;
import com.pfeproject.kanbanboard.repository.SectionRepository;
import com.pfeproject.kanbanboard.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        /*List<Section> sections = sectionRepository.getSectionsBySession(getSection(id).getSession().getIdSession());
        for (Section sec:sections) {
            if (sec.getIdSection() != id) {
                if (Objects.equals(sec.getNameSection(), updated.getNameSection())) {
                    return null;
                }
            }
        }*/
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
        if (sectionRepository.getSectionByNameSectionPerSession(new_Section.getSession().getIdSession(), new_Section.getNameSection()) != null || sessionRepository.findById(new_Section.getSession().getIdSession()).isEmpty())
        {
            return null;
        }
        return sectionRepository.save(new_Section);
    }
}
