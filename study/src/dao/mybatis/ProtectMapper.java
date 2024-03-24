package dao.mybatis;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.ProtectDao;
import entity.Protect;

public class ProtectMapper extends SqlSessionDaoSupport implements ProtectDao {

	public void create(Protect obj) {
		getSqlSession().insert("Protect.create", obj);
	}

	public Protect read(Long id) { 
		return getSqlSession().selectOne("Protect.read", id);
	}

	public void update(Protect obj) {
		getSqlSession().update("Protect.update", obj);
	}

	public void delete(Long id) {
		getSqlSession().delete("Protect.delete", id);
	}

	public Protect FindByDocs(Long id) {
		return getSqlSession().selectOne("Protect.FindByDocs", id);
	}
	
	@Override
	public List<Protect> findAll() {
		return getSqlSession().selectList("Protect.findAll");
	}

	@Override
	public List<Protect> findByUserId(Long id) {
		return getSqlSession().selectList("Protect.findByUserId", id);
	}

	@Override
	public void deleteByUserId(Long id) {
		getSqlSession().delete("Protect.deleteByUserId", id);
	}

	@Override
	public List<Protect> findDocsByDate(Date date) {
		return getSqlSession().selectList("Protect.findProtectByDate", date);
	}

}
