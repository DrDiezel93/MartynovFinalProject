package dao;

import java.util.List;

import entity.User;

public interface UserDao extends CrudDao<Long, User> {
    
    public List<User> findAll(); 

    public User findByName(String name);
    
    public User findByEmail(String name);
    
    public void updateNoPass(User obj);
        
    public List<User> findByTwo(String real,String display);
    
    public List<User> findByThree(String real,String display,String spec);
    
    public List<User> findByReal(String real);
    
    public List<User> findByDispaly(String display);
    
    public List<User> findBySpec(String spec);
    
    public List<User> findByDiplaySpec(String spec,String display);
    
    public List<User> findByRealSpec(String spec,String real);

}