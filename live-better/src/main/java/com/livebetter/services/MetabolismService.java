package com.livebetter.services;
import com.livebetter.domain.Metabolism;
import java.util.List;

public interface MetabolismService {

	public abstract long countAllMetabolismses();


	public abstract void deleteMetabolisms(Metabolism metabolism);


	public abstract Metabolism findMetabolisms(Long id);


	public abstract List<Metabolism> findAllMetabolismses();


	public abstract List<Metabolism> findMetabolismsEntries(int firstResult, int maxResults);


	public abstract void saveMetabolisms(Metabolism metabolism);


	public abstract Metabolism updateMetabolisms(Metabolism metabolism);

}
