package dao;

import java.util.List;

import entity.Docs;

public interface DocsDao extends CrudDao<Long, Docs> {
    
    public List<Docs> findAll();

    public List<Docs> findByUserId(Long id);
    
    void deleteByUserId(Long id);
    
    public List<Docs> selectTop20();
    
    public List<Docs> findDocsByDate(Docs docs);
    
    public List<Docs> findDocsByName(Docs docs);
    
    public List<Docs> findByTwo(Docs docs);
    
    public List<Docs> findDocsSpec();
    
    public List<Docs> findDocsAsp();
    
    public List<Docs> findDocsMag();
    
    public List<Docs> findDocsBak(); 
}
