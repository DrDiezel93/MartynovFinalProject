package dao;

import java.util.Date;
import java.util.List;

import entity.Protect;

public interface ProtectDao extends CrudDao<Long, Protect> {

	public List<Protect> findAll();

	public List<Protect> findByUserId(Long id);

	void deleteByUserId(Long id);

	public List<Protect> findDocsByDate(Date date);
	
	public Protect FindByDocs(Long id); 

}
