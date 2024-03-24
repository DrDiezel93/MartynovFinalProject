package dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.ReportsDao;
import entity.Reports;

public class ReportsMapper extends SqlSessionDaoSupport implements ReportsDao {

	public void create(Reports obj) {
		getSqlSession().insert("Reports.create", obj); 
	}

	public Reports read_reports(Reports reports) {
		return getSqlSession().selectOne("Reports.read_reports", reports);
	}
	
	public Reports read(Long id) {
		return getSqlSession().selectOne("Reports.read", id);
	}

	public void update(Reports obj) {
		getSqlSession().update("Reports.update", obj);
	}

	public void delete(Long id) {
		getSqlSession().delete("Reports.delete", id);
	}

	public List<Reports> findAll() {
		return getSqlSession().selectList("Reports.findAll");
	}


	@Override
	public List<Reports> findByDocs(Long id) {
		return getSqlSession().selectList("Reports.findByDocs", id);
	}

}
