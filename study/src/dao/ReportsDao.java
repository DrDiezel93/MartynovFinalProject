package dao;

import java.util.List;

import entity.Reports;


public interface ReportsDao extends CrudDao<Long, Reports> {
	
    public List<Reports> findAll();
        
    public Reports read_reports(Reports reports);
    
    public List<Reports> findByDocs(Long id);  
    
    

    
}
