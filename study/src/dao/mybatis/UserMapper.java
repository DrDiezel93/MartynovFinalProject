package dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.UserDao;
import entity.User;

public class UserMapper extends SqlSessionDaoSupport implements UserDao {

    public void create(User obj) {
        getSqlSession().insert("User.create", obj);
    }

    public User read(Long id) {
        return getSqlSession().selectOne("User.read", id); 
    }

    public void update(User obj) {
        getSqlSession().update("User.update", obj);
    }

    public void delete(Long id) {
        getSqlSession().delete("User.delete", id);
    }

    @Override
    public List<User> findAll() {
        return getSqlSession().selectList("User.findAll");
    }

    @Override
    public User findByName(String name) {
        return getSqlSession().selectOne("User.findByName", name);
    }

    @Override
    public User findByEmail(String name) {
        return getSqlSession().selectOne("User.findByEmail", name);
    }

    @Override
    public void updateNoPass(User obj) {
        getSqlSession().update("User.updateNoPass", obj);

    }

    @Override
    public List<User> findByTwo(String real, String display) {
        Map<String, String> map = new HashMap<>();
        map.put("real", real);
        map.put("display", display);
        return getSqlSession().selectList("User.findByTwo", map);
    }

    @Override
    public List<User> findByReal(String real) {
        return getSqlSession().selectList("User.findByReal", real);
    }

    @Override
    public List<User> findByDispaly(String display) {
        return getSqlSession().selectList("User.findByDispaly", display);
    }

	@Override
	public List<User> findBySpec(String spec) {
		return getSqlSession().selectList("User.findBySpec", spec);
	}

	@Override
	public List<User> findByDiplaySpec(String spec, String display) {
        Map<String, String> map = new HashMap<>();
        map.put("spec", spec);
        map.put("display", display);
        return getSqlSession().selectList("User.findByDiplaySpec", map);
	}

	@Override
	public List<User> findByRealSpec(String spec, String real) {
        Map<String, String> map = new HashMap<>();
        map.put("spec", spec);
        map.put("real", real);
        return getSqlSession().selectList("User.findByRealSpec", map);
	}

	@Override
	public List<User> findByThree(String real, String display, String spec) {
        Map<String, String> map = new HashMap<>();
        map.put("real", real);
        map.put("display", display);
        map.put("spec", spec);
        return getSqlSession().selectList("User.findByThree", map);
	}

}
