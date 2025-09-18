package co.edu.unbosque.artemisa.service;

import java.util.List;

public interface CRUDOperation <D>{
	
	public int create(D data);
	
	public List<D> getAll();
	
	public int deleteById(Long id);
	
	public int updateById(Long id,D newData);
	
	public long count();
	
	public boolean exist(Long id);
}
