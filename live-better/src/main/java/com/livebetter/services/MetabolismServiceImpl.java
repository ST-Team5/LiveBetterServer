package com.livebetter.services;

import com.livebetter.domain.Metabolism;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MetabolismServiceImpl implements MetabolismService {

	public long countAllMetabolismses() {
        return Metabolism.countMetabolismses();
    }

	public void deleteMetabolisms(Metabolism metabolism) {
        metabolism.remove();
    }

	public Metabolism findMetabolisms(Long id) {
        return Metabolism.findMetabolisms(id);
    }

	public List<Metabolism> findAllMetabolismses() {
        return Metabolism.findAllMetabolismses();
    }

	public List<Metabolism> findMetabolismsEntries(int firstResult, int maxResults) {
        return Metabolism.findMetabolismsEntries(firstResult, maxResults);
    }

	public void saveMetabolisms(Metabolism metabolism) {
        metabolism.persist();
    }

	public Metabolism updateMetabolisms(Metabolism metabolism) {
        return metabolism.merge();
    }
}
